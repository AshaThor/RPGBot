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

@Component
public class DiceParser {

    private static final Logger logger = LogManager.getLogger(DiceParser.class);

    @Autowired
    private Dice dice;
    @Autowired
    private MessageParser messageParser;

    public void parser(@Nonnull MessageReceivedEvent event, String[] userCommand){
        int volume = 0, sides = 0;

        MessageChannel channel = event.getChannel();
        //Itterate through the string list and cut out the d
        for(int i = 1; i < userCommand.length; i++){
            String[] commandParts = userCommand[i].split(("d"));
            logger.info(commandParts);
            volume = Integer.parseInt(commandParts[0]);
            sides = Integer.parseInt(commandParts[1]);
        }
        String diceString = dice.roll(volume,sides).toString();
        channel.sendMessage(diceString).queue();
        logger.info(diceString);
    }
}
