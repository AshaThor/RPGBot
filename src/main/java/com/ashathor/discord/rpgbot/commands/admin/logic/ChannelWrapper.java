package com.ashathor.discord.rpgbot.commands.admin.logic;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class ChannelWrapper {
    public void create(@Nonnull MessageReceivedEvent event){
        event.getGuild().createTextChannel("test3").queue();
    }
}
