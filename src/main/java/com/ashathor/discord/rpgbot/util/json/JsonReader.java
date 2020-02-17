package com.ashathor.discord.rpgbot.util.json;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


@Component
public class JsonReader {

    private static final Logger logger = LogManager.getLogger(JsonReader.class);

    public JSONObject readFile(String path) {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(path)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            logger.error("Error in Reading File");
            logger.error(e.getMessage());
            return null;
        }
    }
}
