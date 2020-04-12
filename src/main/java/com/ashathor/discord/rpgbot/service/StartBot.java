package com.ashathor.discord.rpgbot.service;

import com.ashathor.discord.rpgbot.util.TextFileReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
public class StartBot {
    @Autowired
    private MessageListener listener;
    @Autowired
    private TextFileReader textFileReader;
    @Value("${jda.token}")
    private String token;

    @PostConstruct
    public void init() throws LoginException {
        //Create a JDA instance with the token
        JDA jda = new JDABuilder(token).build();
        //Add our event listener to the JDA instance
        jda.addEventListener(listener);
    }
}
