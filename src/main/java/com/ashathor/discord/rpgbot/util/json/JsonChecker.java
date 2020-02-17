package com.ashathor.discord.rpgbot.util.json;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JsonChecker {
    @Autowired
    private JsonReader jsonReader;
    @Autowired
    private JsonWriter jsonWriter;

    public JSONObject checkAndMake(String path, Long playerId) {
        File tmpDir = new File(path);
        if (tmpDir.exists()) {
            return jsonReader.readFile(path);
        } else {
            return jsonWriter.makeFile(path, playerId);
        }
    }
}
