package com.ashathor.discord.rpgbot.discord.service;

import com.ashathor.discord.rpgbot.discord.commands.admin.InitiativeQueue;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

@Component
public class ReactionListener extends ListenerAdapter {

    private static final Logger logger = LogManager.getLogger(ReactionListener.class);

    @Autowired
    private InitiativeQueue initiativeQueue;

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;
        List<Message> messages = new MessageHistory(event.getChannel()).retrievePast(10).complete();
        for (Message msg : messages) {
            try {
                if (msg.getEmbeds().get(0).getTitle().equals("Initiative Queue")) {
                    initiativeQueue.update(event, msg);
                }
            } catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }
}

