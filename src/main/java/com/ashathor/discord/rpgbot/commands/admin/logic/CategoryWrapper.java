package com.ashathor.discord.rpgbot.commands.admin.logic;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class CategoryWrapper {

    private static final Logger logger = LogManager.getLogger(CategoryWrapper.class);

    public void create(@Nonnull MessageReceivedEvent event){
        event.getGuild().createCategory("test 3").queue();
        logger.info("Create Category Success");

    }
}
