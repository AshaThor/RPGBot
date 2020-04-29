package com.ashathor.discord.rpgbot.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class InitiativeQueue {
    public void create(@Nonnull MessageReceivedEvent event, String[] userCommand){
        String[] userArray = new String[Integer.parseInt(userCommand[1])] ;
        EmbedBuilder teb = new EmbedBuilder();
        teb.setTitle("Initiative Queue");
        teb.setDescription("Click the emoji to roll initiative");
        teb.addField("Players", String.valueOf(userArray.length),true);
        event.getChannel().sendMessage(teb.build()).queue(message -> message.addReaction("U+1F3B2").queue());
    }
}
