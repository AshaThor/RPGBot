package com.ashathor.discord.rpgbot.commands.player;

import com.ashathor.discord.rpgbot.util.Help;
import com.ashathor.discord.rpgbot.util.json.JsonChecker;
import com.ashathor.discord.rpgbot.util.json.JsonReader;
import com.ashathor.discord.rpgbot.util.json.JsonWriter;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class PlayerCharacter {

    @Autowired
    private JsonChecker jsonChecker;
    @Autowired
    private JsonWriter jsonWriter;
    @Autowired
    private JsonReader jsonReader;
    @Autowired
    private Help help;

    private MessageChannel channel;

    private static final Logger logger = LogManager.getLogger(PlayerCharacter.class);

    public void playerCharacter(@Nonnull MessageReceivedEvent event, String[] userCommand){
        //!player name class level race alignment age exp
        this.channel = event.getChannel();
        Long playerID = getPlayerNumber(event);
        String path = makePath(playerID);
        if (userCommand.length < 2) {
            displayCharacter(path);
        } else {
            switch (userCommand[1]) {
                case "create": {
                    makeCharacterWithName(playerID, userCommand, path);
                    break;
                }
                //case "set": {
                //    break;
                //}
                //case "get":{
                //    break;
                //}
                case "help": {
                    channel.sendMessage(help.help("playerHelp")).queue();
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
            JSONObject characterFileJson = jsonChecker.checkAndMake(path, playerID);
            characterFileJson.put("character-name", userCommand[2]);
            jsonWriter.updateField(characterFileJson, path);
            channel.sendMessage("Your Character has been named / renamed").queue();
            displayCharacter(path);
        } else {
            channel.sendMessage("Please specify a name for your character").queue();
        }
    }

    private String makePath(Long playerId) {
        return "c:\\dnd\\" + playerId + ".json";
    }

    private Long getPlayerNumber(@Nonnull MessageReceivedEvent event) {
        return event.getMember().getIdLong();
    }

    private void displayCharacter(String path) {
        try {
            JSONObject characterFileJson = jsonReader.readFile(path);
            channel.sendMessage("Character name: " + characterFileJson.get("character-name").toString()).queue();
        } catch (NullPointerException n) {
            channel.sendMessage("You do not have a character").queue();
        }
    }
}

