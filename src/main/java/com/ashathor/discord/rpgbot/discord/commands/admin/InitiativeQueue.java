package com.ashathor.discord.rpgbot.discord.commands.admin;

import com.ashathor.discord.rpgbot.discord.dice.logic.Dice;
import com.ashathor.discord.rpgbot.discord.util.MessageParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Random;

@Component
public class InitiativeQueue {
    @Autowired
    private Dice dice;

    @Autowired
    private MessageParser messageParser;

    @Autowired
    private InitiativeNumbers initiativeNumbers;

    private static final Logger logger = LogManager.getLogger(InitiativeQueue.class);

    public void create(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        String[] userArray = new String[Integer.parseInt(userCommand[1])];
        EmbedBuilder teb = new EmbedBuilder();
        teb.setTitle("Initiative Queue");
        teb.setDescription("Click the emoji to roll initiative");
        event.getChannel().sendMessage(teb.build()).queue(message -> message.addReaction("U+1F3B2").queue());
    }

    public void update(@Nonnull MessageReactionAddEvent event, Message msg) {
        String[] name = messageParser.parser(event.getMember().getEffectiveName(), " ");
        EmbedBuilder eb = new EmbedBuilder(msg.getEmbeds().get(0));
        eb.setTitle("Initiative Queue");
        eb.setDescription("Click the emoji to roll initiative");
        Integer die = new Random().nextInt(20) + 1;
        eb.addField(name[0], String.valueOf((die + initiativeNumbers.getInitiatives(name[0]))), false);
        logger.info(die);
        logger.info(initiativeNumbers.getInitiatives(name[0]));
        event.getChannel().editMessageById(msg.getId(), eb.build()).queue();
    }
}
