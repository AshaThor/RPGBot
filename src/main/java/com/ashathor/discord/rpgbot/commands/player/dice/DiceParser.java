package com.ashathor.discord.rpgbot.commands.player.dice;

import com.ashathor.discord.rpgbot.commands.player.dice.logic.Dice;
import com.ashathor.discord.rpgbot.util.MessageParser;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DiceParser {

    private static final Logger logger = LogManager.getLogger(DiceParser.class);

    @Autowired
    private Dice dice;
    @Autowired
    private MessageParser messageParser;

    private int[] parser(String[] userCommand, String parserChar) {
        //Itterate through the string list and cut out the d
        for (int i = 1; i < userCommand.length; i++) {
            String[] commandParts = messageParser.parser(userCommand[i], parserChar);
            int[] commandPartsInt = new int[commandParts.length];
            for (int j = 0; j < commandParts.length; j++) {
                commandPartsInt[j] = Integer.parseInt(commandParts[j]);
            }
            logger.info(commandPartsInt);
            return commandPartsInt;
            //commandParts[1]
            //volume = Integer.parseInt(commandParts[0]);
            //sides = Integer.parseInt();

            //ArrayList<Integer> rolls = dice.roll(volume, sides);
            //String diceString = diceHumanForm(rolls) + " Sum: " + dice.sum(rolls);
            //logger.info(diceString);
        }
        return null;
    }

    public void rawDice(MessageChannel channel, String[] userCommand) {
        String[] userCommandForDice = messageParser.parser(userCommand, "-r");
        int[] commandParts = parser(userCommandForDice , "d");
        ArrayList<Integer> rolls = dice.roll(commandParts[0], commandParts[1]);
        channel.sendMessage(rolls.toString() + " Sum: " + dice.sum(rolls)).queue();
    }

    /**
     * Dice in Human From
     * <p>
     * This function is to replace a list of rolls
     * "[x,y,z]"
     * into
     * "x[1],y[1],z[1]"
     * This is to allow grouping of same dice
     *
     */
    public void diceHumanForm(MessageChannel channel, String[] userCommand) {
        // 1[2] 2[3] 4[8]
        int[] commandParts = parser(userCommand, "d");
        ArrayList<Integer> rolls = dice.roll(commandParts[0], commandParts[1]);
        String humanFormList = "";
        for (int i = 0; i < rolls.size(); i++) {
            int rollFrequency = 1;
            while (i + 1 < rolls.size())
                if (rolls.get(i).equals(rolls.get(i + 1))) {
                    rollFrequency++;
                    i++;
                } else {
                    break;
                }
            humanFormList += rolls.get(i) + "[" + rollFrequency + "] ";
        }
        channel.sendMessage(humanFormList + " Sum: " + dice.sum(rolls)).queue();
    }
}
