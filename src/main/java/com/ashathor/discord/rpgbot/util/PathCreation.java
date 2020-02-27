package com.ashathor.discord.rpgbot.util;

import org.springframework.stereotype.Component;

@Component
public class PathCreation {
    public String makePath(Long playerId) {
        return "c:\\dnd\\" + playerId + ".json";
    }
}
