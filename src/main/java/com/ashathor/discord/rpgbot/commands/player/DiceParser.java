package com.ashathor.discord.rpgbot.commands.player;

import com.ashathor.discord.rpgbot.commands.player.logic.Dice;
import com.ashathor.discord.rpgbot.util.MessageParser;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;

@Component
public class DiceParser {

    private static final Logger logger = LogManager.getLogger(DiceParser.class);

    @Autowired
    private Dice dice;
    @Autowired
    private MessageParser messageParser;

    public void parser(@Nonnull MessageReceivedEvent event, String[] userCommand){
        int volume = 0;
        int sides = 0;

        MessageChannel channel = event.getChannel();
        //Itterate through the string list and cut out the d
        for(int i = 1; i < userCommand.length; i++){
            String[] commandParts = messageParser.parser(userCommand[i], "d");
            logger.info(commandParts);
            volume = Integer.parseInt(commandParts[0]);
            sides = Integer.parseInt(commandParts[1]);
        }
        String diceString = dice.roll(volume,sides).toString();

        //String diceString = diceHumanForm(dice.roll(volume,sides));
        channel.sendMessage(diceString).queue();
        logger.info(diceString);
    }
    public String diceHumanForm(ArrayList<Integer> rolls){
        // 1[2] 2[3] 4[8]
        for(Integer roll:rolls){
        }

        final Iterator<Integer> varIterator = rolls.iterator();
        while(varIterator.hasNext()){
            Integer var = varIterator.next();
            if(var.equals(varIterator.next())){
                logger.info("ITS EQUAL {} & {}", var , varIterator.next());
            }
            else{
                //Do this insted aka
            }
        }
        return rolls.toString();
    }
}
