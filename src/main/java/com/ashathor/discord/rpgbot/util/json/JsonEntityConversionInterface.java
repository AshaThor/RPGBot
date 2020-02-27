package com.ashathor.discord.rpgbot.util.json;

import com.ashathor.discord.rpgbot.commands.player.character.Character;

import java.io.IOException;

public interface JsonEntityConversionInterface {
    void write(String path, Character character);

    Character read(String path) throws IOException;
}
