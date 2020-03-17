package com.ashathor.discord.rpgbot.service;

import com.ashathor.discord.rpgbot.util.TextFileReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class StartBot {
    @Autowired
    private MessageListener listener;
    @Autowired
    private TextFileReader textFileReader;
    @Value("${jda.token}")
    private String token;

    @PostConstruct
    public void init() throws IOException, LoginException {
        //Reader for the token file to factor it out of code and allow any user to add their own code to the token.txt file
        //BufferedReader tokenReader = textFileReader.read(".\\src\\main\\resources\\token.txt");
        //BufferedReader tokenReader = textFileReader.read("..\\..\\..\\..\\..\\token.txt");
        //File tokenReader = new ClassPathResource("token.txt").getFile();
        //Read the first line from the file that contains the token
        //String token = new String(Files.readAllBytes(tokenReader.toPath()));
        //String token = tokenReader.readLine();
        //Create a JDA instance with the token
        JDA jda = new JDABuilder(token).build();
        //Add our event listener to the JDA instance
        jda.addEventListener(listener);
    }
}
