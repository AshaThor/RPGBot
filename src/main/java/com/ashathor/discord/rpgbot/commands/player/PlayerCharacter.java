package com.ashathor.discord.rpgbot.commands.player;

import com.ashathor.discord.rpgbot.util.json.JsonChecker;
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

    private MessageChannel channel;

    private static final Logger logger = LogManager.getLogger(PlayerCharacter.class);

    public void playerCharacter(@Nonnull MessageReceivedEvent event, String[] userCommand) {
        //!player name class level race alignment age exp
        this.channel = event.getChannel();
        String path = makePath(event);
        switch (userCommand.length) {
            case 2: {
                makeCharacterWithName(userCommand, path);
                break;
            }
            case 7: {

                break;
            }
        }

        //Long memberId = event.getMember().getIdLong();
        //logger.info(memberId);
        //channel.sendMessage(jsonObject.get("character-name").toString()).queue();
        // loop array
        //JSONArray msg = (JSONArray) jsonObject.get("messages");
        //Iterator<String> iterator = msg.iterator();
        ///while (iterator.hasNext()) {
        //    System.out.println(iterator.next());
        ///}
        //JSONObject obj = new JSONObject();
        //obj.put("player", memberId);
        //obj.put("character-name", userCommand[1]);
    }

    private void makeCharacterWithName(String[] userCommand, String path) {
        JSONObject characterFileJson = jsonChecker.checkAndMake(path);
        characterFileJson.put("character-name",userCommand[1]);
        jsonWriter.updateField(characterFileJson,path);
        channel.sendMessage(characterFileJson.get("character-name").toString()).queue();
    }

    private String makePath(@Nonnull MessageReceivedEvent event) {
        return "c:\\dnd\\" + event.getMember().getIdLong() + ".json";
    }
}

