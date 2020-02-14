package com.ashathor.discord.rpgbot.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class MessageParser {
    public String[] spaceParser(@Nonnull MessageReceivedEvent event){
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        String[] userMessage = content.split((" "));
        return userMessage;
    }
}
