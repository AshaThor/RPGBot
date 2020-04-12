package com.ashathor.discord.rpgbot.commands.player.character;

import com.ashathor.discord.rpgbot.util.json.JsonEntityConversionInterface;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.IOException;

@Component
public class CharacterSetCommand {

    @Autowired
    private JsonEntityConversionInterface jsonEntityConversion;

    public void command(String[] userCommand, String path) {
        try {
            Character playerCharacter = jsonEntityConversion.read(path);
            switch (userCommand[2]) {
                case "name": {
                    playerCharacter.setCharacterName(userCommand[3]);
                    write(path, playerCharacter);
                    break;
                }
                case "class": {
                    playerCharacter.setCharacterClass(userCommand[3]);
                    write(path, playerCharacter);
                    break;
                }
                case "level": {
                    playerCharacter.setCharacterLevel(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "background": {
                    playerCharacter.setCharacterBackground(userCommand[3]);
                    write(path, playerCharacter);
                    break;
                }
                case "race": {
                    playerCharacter.setCharacterRace(userCommand[3]);
                    write(path, playerCharacter);
                    break;
                }
                case "alignment": {
                    playerCharacter.setCharacterAlignment(userCommand[3]);
                    write(path, playerCharacter);
                    break;
                }
                case "exp": {
                    playerCharacter.setCharacterExp(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "strength": {
                    playerCharacter.setCharacterStrength(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "dexterity":{
                    playerCharacter.setCharacterDexterity(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "constitution":{
                    playerCharacter.setCharacterConstitution(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "intelligence":{
                    playerCharacter.setCharacterIntelligence(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "wisdom":{
                    playerCharacter.setCharacterWisdom(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "charisma":{
                    playerCharacter.setCharacterCharisma(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                case "inspiration":{
                    playerCharacter.setCharacterInspiration(Integer.parseInt(userCommand[3]));
                    write(path, playerCharacter);
                    break;
                }
                default:{
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            //catch
        }
    }

    private void write(String path, Character playerCharacter) {
        jsonEntityConversion.write(path, playerCharacter);
    }
}

