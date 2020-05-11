package com.ashathor.discord.rpgbot.discord.commands.admin;

import org.springframework.stereotype.Component;

@Component
public class InitiativeNumbers {

    public int getInitiatives(String name){
        switch (name.toLowerCase()){
            //DnD Gremlins
            case "gloin":
                return 1;
            case "fronklin":
                return 3;
            case "ekemon":
                return 3;
            case "rangrim":
                return 1;
                
            //Hardcore DnD Goblins
            case "moko":
                return 1;
            case "naal":
                return 3;
            case "travok":
                return 1;
            case "eildi":
                return 1;
            case "thokki":
                return 1;
            case "ting":
                return 3;
                
                
            default:
                break;
        }
        return 0;
    }
}