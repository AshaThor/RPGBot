package com.ashathor.discord.rpgbot.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class Help {

    private static final Logger logger = LogManager.getLogger(Help.class);

    @Autowired
    private TextFileReader textFileReader;

    public String help(String location) {
        String message = "";
        try {
            BufferedReader reader = textFileReader.read("..\\src\\main\\resources\\" + location + ".txt");
            logger.info("{} file has been successfully read", location);
            String line = reader.readLine();
            while (line != null) {
                message = message.concat("\n" + line);
                // read next line
                line = reader.readLine();
            }
            return message;
        } catch (IOException e) {
            logger.error("Help file read was not successful: ${}", e.getMessage());
            return "Help file read was not successful";
        }
    }
}
