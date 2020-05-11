package com.ashathor.discord.rpgbot.discord.commands.admin.logic;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Arrays;

@Component
public class CategoryWrapper {

    private static final Logger logger = LogManager.getLogger(CategoryWrapper.class);

    /**
     * Creates a {@link net.dv8tion.jda.api.entities.Category} and names it
     *
     * @param event {@link net.dv8tion.jda.api.events.message}
     * @param name Name given to the category created
     * */
    public void create(@Nonnull MessageReceivedEvent event, String name) {
        event.getGuild().createCategory(name).queue();
    }

    public void createWithChildren(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        event.getGuild().createCategory(userCommand[1]).queue(category -> {
                    String[] list = {"notes", "dice", "character", "character-backstory"};
                    Arrays.stream(list).forEach(s ->
                            event.getGuild().createTextChannel(s).queue(channel ->
                                    channel.getManager().setParent((Category) category).queue()
                            )
                    );
                }
        );
        logger.info("create with children has been called");
    }
}
