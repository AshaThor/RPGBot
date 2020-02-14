package com.ashathor.discord.rpgbot.service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
@Component
public class StartBot {
    @Autowired
    private MessageListener listener;

    @PostConstruct
    public void init() throws IOException, LoginException {
        //Reader for the token file to factor it out of code and allow any user to add their own code to the token.txt file
        BufferedReader tokenReader = new BufferedReader(new FileReader(".\\src\\main\\resources\\token.txt"));
        //Read the first line from the file that contains the token
        String token = tokenReader.readLine();
        //Create a JDA instance with the token
        JDA jda = new JDABuilder(token).build();
        //Add our event listener to the JDA instance
        jda.addEventListener(listener);
    }
}
