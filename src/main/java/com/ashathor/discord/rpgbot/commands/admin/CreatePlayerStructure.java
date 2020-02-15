package com.ashathor.discord.rpgbot.commands.admin;

import com.ashathor.discord.rpgbot.commands.admin.logic.CategoryWrapper;
import com.ashathor.discord.rpgbot.commands.admin.logic.ChannelWrapper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class CreatePlayerStructure {

    private static final Logger logger = LogManager.getLogger(CreatePlayerStructure.class);

    @Autowired
    private CategoryWrapper categoryWrapper;
    @Autowired
    private ChannelWrapper channelWrapper;
    public void makeCategory(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        categoryWrapper.create(event, userCommand[1]);
    }

    public void makeCategoryWithChildren(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        categoryWrapper.createWithChildren(event, userCommand);
    }
    public void makeChannel(@Nonnull MessageReceivedEvent event, String[] userCommand){
        channelWrapper.create(event,userCommand[1]);
    }
}
