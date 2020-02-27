package com.ashathor.discord.rpgbot.util.json;

import com.ashathor.discord.rpgbot.commands.player.character.Character;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonEntityConversion implements JsonEntityConversionInterface {
    private static final Logger logger = LogManager.getLogger(JsonEntityConversion.class);

    @Override
    public void write(String path, Character character) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Java object to JSON file
            mapper.writeValue(new File(path), character);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Character read(String path) throws IOException {
        //JSON file to Java object
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Character.class);
    }
}
