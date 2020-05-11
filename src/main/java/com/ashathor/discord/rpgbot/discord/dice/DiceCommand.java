package com.ashathor.discord.rpgbot.discord.dice;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class DiceCommand {

    @Autowired
    private DiceParser diceParser;

    public void command(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        MessageChannel channel = event.getChannel();
        if ("-r".equals(userCommand[1])) {
            diceParser.rawDice(channel, userCommand);
        } else {
            diceParser.diceHumanForm(channel, userCommand);
        }
    }
}
