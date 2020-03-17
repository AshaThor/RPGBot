package com.ashathor.discord.rpgbot.random;
import org.brunocvcunha.jiphy.Jiphy;
import org.brunocvcunha.jiphy.JiphyConstants;
import org.brunocvcunha.jiphy.model.JiphySearchResponse;
import org.brunocvcunha.jiphy.requests.JiphySearchRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Gifly {
    Jiphy jiphy = Jiphy.builder()
            .apiKey(JiphyConstants.API_KEY_BETA)
            .build();
    public String getGif(String data) {
        try {
            JiphySearchResponse returnedGif = jiphy.sendRequest(new JiphySearchRequest(data));
            return (returnedGif.getData().get(0).getUrl());
        }
        catch (IOException e){
            return "Sorry could not get you a Gif";
        }
    }
}