package com.ashathor.discord.rpgbot.service;

import com.ashathor.discord.rpgbot.commands.admin.CreatePlayerStructure;
import com.ashathor.discord.rpgbot.commands.admin.InitiativeQueue;
import com.ashathor.discord.rpgbot.commands.player.character.CharacterCommand;
import com.ashathor.discord.rpgbot.commands.player.dice.DiceCommand;
import com.ashathor.discord.rpgbot.random.Gifly;
import com.ashathor.discord.rpgbot.util.MessageParser;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
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
    private DiceCommand diceCommand;
    @Autowired
    private CharacterCommand characterCommand;
    @Autowired
    private Gifly gifly;
    @Autowired
    private InitiativeQueue initiativeQueue;

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        String[] userCommand = messageParser.parser(event, " ");
        MessageChannel channel = event.getChannel();
        switch (userCommand[0]) {
            case "!ping": {
                channel.sendMessage("Pong!").queue();
                break;
            }
            case "!dice": {
                logger.info("Roll Dice Command Called");
                diceCommand.command(event, userCommand);
                break;
            }
            case "!makePlayerStructure": {
                logger.info("Create Player Structure Command Called {}", userCommand[1]);
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
                characterCommand.command(event, userCommand);
                break;
            }
            case "!gif":{
                channel.sendMessage(gifly.getGif(userCommand[1])).queue();
                break;
            }
            case "!initiative":{
                initiativeQueue.create(event,userCommand);
                break;
            }
            default: {
                logger.warn("Unrecognised command: {}", event.getMessage().getContentRaw());
                break;
            }
        }
    }
}
