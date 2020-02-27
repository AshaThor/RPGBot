package com.ashathor.discord.rpgbot.commands.player.character;

import com.ashathor.discord.rpgbot.util.Help;
import com.ashathor.discord.rpgbot.util.PathCreation;
import com.ashathor.discord.rpgbot.util.json.JsonEntityConversionInterface;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.IOException;

@Component
public class CharacterCommand {

    @Autowired
    private Help help;
    @Autowired
    private CharacterEmbededBuilder characterEmbededBuilder;
    @Autowired
    private JsonEntityConversionInterface jsonEntityConversion;
    @Autowired
    private PathCreation pathCreation;
    @Autowired
    private CharacterSetCommand characterSetCommand;

    private MessageChannel channel;

    private static final Logger logger = LogManager.getLogger(CharacterCommand.class);

    /**
     * Test
     *
     * @param event
     * @param userCommand
     */
    public void command(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        //!player name class level race alignment age exp
        this.channel = event.getChannel();
        Long playerID = event.getMember().getIdLong();
        String path = pathCreation.makePath(playerID);
        if (userCommand.length < 2) {
            displayCharacter(path, true, false, false, false);
        } else {
            switch (userCommand[1]) {
                case "create": {
                    break;
                }
                case "set": {
                    characterSetCommand.command(event, userCommand, path);
                    break;
                }
                //case "get":{
                //    break;
                //}
                case "help": {
                    channel.sendMessage(help.help("playerHelp")).queue();
                    break;
                }
                case "stats": {
                    displayCharacter(path, false, true, false, false);
                    break;
                }
                default: {
                    channel.sendMessage("This is not a valid command, please type !player help for a list of commands").queue();
                }
            }
        }
    }


    private void makeCharacterWithName(Long playerID, String[] userCommand, String path) {
        if (userCommand.length > 2) {
            Character playerCharacter = new Character(playerID);
            playerCharacter.setCharacterName(userCommand[2]);
            jsonEntityConversion.write(path, playerCharacter);
            channel.sendMessage("Your Character has been named / renamed").queue();
        } else {
            channel.sendMessage("Please specify a name for your character").queue();
        }
    }


    private void displayCharacter(String path, boolean info, boolean stats, boolean prof, boolean save) {
        try {
            if (info) {
                characterEmbededBuilder.info(channel, jsonEntityConversion.read(path));
            } else if (stats) {
                characterEmbededBuilder.stats(channel, jsonEntityConversion.read(path));
            } else if (prof) {
            } else if (save) {
            }
        } catch (NullPointerException n) {
            channel.sendMessage("You do not have a character").queue();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}