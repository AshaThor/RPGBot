package com.ashathor.discord.rpgbot.service;

import com.ashathor.discord.rpgbot.commands.admin.CreatePlayerStructure;
import com.ashathor.discord.rpgbot.commands.player.DiceParser;
import com.ashathor.discord.rpgbot.commands.player.PlayerCharacter;
import com.ashathor.discord.rpgbot.util.MessageParser;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class MessageListener extends ListenerAdapter {

    private static final Logger logger = LogManager.getLogger(MessageListener.class);

    @Autowired
    private CreatePlayerStructure playerStructure;
    @Autowired
    private MessageParser messageParser;
    @Autowired
    private DiceParser diceParser;
    @Autowired
    private PlayerCharacter playerCharacter;


    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        String[] userCommand = messageParser.parser(event, " ");
        switch (userCommand[0]) {
            case "!ping": {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Pong!").queue();
                break;
            }
            case "!dice": {
                logger.info("Roll Dice Command Called");
                diceParser.parser(event, userCommand);
                break;
            }
            case "!makePlayerStructure": {
                logger.info("Create Player Structure Command Called {}", userCommand[2]);
                playerStructure.makeCategoryWithChildren(event, userCommand);
                break;
            }
            case "!makeCategory": {
                playerStructure.makeCategory(event, userCommand);
                break;
            }
            case "!makeChannel":{
                playerStructure.makeChannel(event, userCommand);
                break;
            }
            case "!player":{
                playerCharacter.playerCharacter(event, userCommand);
                break;
            }
            default: {
                logger.warn("Unrecognised command: {}", event.getMessage().getContentRaw());
            }
        }
    }
}
