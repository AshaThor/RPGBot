package com.ashathor.discord.rpgbot.service;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.requests.RestAction;
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
        /*
        * After alot of googling
        * https://www.programcreek.com/java-api-examples/?code=jagrosh/GiveawayBot/GiveawayBot-master/src/main/java/com/jagrosh/giveawaybot/entities/Giveaway.java
        * is what i need
        * will pick up on it tomorrow
        * day 3 out!
        * */
        }
    }

