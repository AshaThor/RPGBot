package com.ashathor.discord.rpgbot.discord.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class MessageParser {
    public String[] parser(@Nonnull MessageReceivedEvent event, String character) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        return content.split((character));
    }

    public String[] parser(String raw, String character) {
        return raw.split((character));
    }

    public String[] parser(String[] raw, String character) {
        String[] commandParts = new String[raw.length];
        for (int i = 1; i < raw.length; i++) {
             parser(raw[i], character);
        }
        return commandParts;
    }
}
