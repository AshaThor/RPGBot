package com.ashathor.discord.rpgbot.util.json;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class JsonWriter {

    private static final Logger logger = LogManager.getLogger(JsonWriter.class);

    @Autowired
    private JsonReader jsonReader;

    public JSONObject makeFile(String path) {
        JSONObject obj = new JSONObject();
        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toJSONString());
            return jsonReader.readFile(path);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public JSONObject updateField(JSONObject jsonObject, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonObject.toJSONString());
            return jsonObject;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
