package com.ashathor.discord.rpgbot.service;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class ReactionListener extends ListenerAdapter {

    private static final Logger logger = LogManager.getLogger(ReactionListener.class);

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event){
        if(event.getUser().isBot()) return;
        Long messageID = event.getMessageIdLong();

        }
    }

