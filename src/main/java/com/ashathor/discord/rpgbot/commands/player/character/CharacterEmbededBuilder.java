package com.ashathor.discord.rpgbot.commands.player.character;

import lombok.NonNull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class CharacterEmbededBuilder {
    private static final Logger logger = LogManager.getLogger(CharacterEmbededBuilder.class);

    public void info(MessageChannel channel, @NonNull Character playerCharacter) {
        EmbedBuilder teb = new EmbedBuilder();
        teb.setTitle(playerCharacter.getCharacterName(), null);
        teb.setDescription("Text");
        try {
            logger.info("Player Id added");
            teb.addField("Class",
                    playerCharacter.getCharacterClass(),
                    //"test",
                    true);
            logger.info("Class");
            teb.addField("Background",
                    playerCharacter.getCharacterBackground(),
                    //"test",
                    true);
            logger.info("Background");
            teb.addField("Race",
                    playerCharacter.getCharacterRace(),
                    //"test",
                    true);
            logger.info("Race");
            teb.addField("Alignment",
                    playerCharacter.getCharacterAlignment(),
                    //"test",
                    true);
            logger.info("Alignment");
            teb.addField("Exp",
                    playerCharacter.getCharacterExp().toString(),
                    //"test",
                    true);
            logger.info("Exp");
            teb.addField("Level",
                    playerCharacter.getCharacterLevel().toString(),
                    //"test",
                    true);
            logger.info("Level");
            channel.sendMessage(teb.build()).queue();
        } catch (IllegalArgumentException e) {
            logger.error("Insufficient data in the character file");
            channel.sendMessage("Sorry you have an incomplete character, please enter more data").queue();
        }
    }
    public void stats(MessageChannel channel, @NonNull Character playerCharacter) {
        EmbedBuilder teb = new EmbedBuilder();
        teb.setTitle(playerCharacter.getCharacterName(), null);
        try {
            logger.info("Stats trying to be shown");
            teb.addField("Strength",
                    playerCharacter.getCharacterStrength().toString() + " [" + modifier(playerCharacter.getCharacterStrength()) + "]",
                    true);
            logger.info("Strength");
            teb.addField("Dexterity",
                    playerCharacter.getCharacterDexterity().toString() + " [" + modifier(playerCharacter.getCharacterDexterity()) + "]",
                    true);
            logger.info("Dexterity");
            teb.addField("Constitution",
                    playerCharacter.getCharacterConstitution().toString() + " [" + modifier(playerCharacter.getCharacterConstitution()) + "]",
                    //"test",
                    true);
            logger.info("Constitution");
            teb.addField("Intelligence",
                    playerCharacter.getCharacterIntelligence().toString() + " [" + modifier(playerCharacter.getCharacterIntelligence()) + "]",
                    //"test",
                    true);
            logger.info("Intelligence");
            teb.addField("Wisdom",
                    playerCharacter.getCharacterWisdom().toString() + " [" + modifier(playerCharacter.getCharacterWisdom()) + "]",
                    //"test",
                    true);
            logger.info("Wisdom");
            teb.addField("Charisma",
                    playerCharacter.getCharacterCharisma().toString() + " [" + modifier(playerCharacter.getCharacterCharisma()) + "]",
                    //"test",
                    true);
            logger.info("Charisma");
            channel.sendMessage(teb.build()).queue();
        } catch (IllegalArgumentException e) {
            logger.error("Insufficient data in the character file");
            channel.sendMessage("Sorry you have an incomplete character, please enter more data").queue();
        }
    }
    private int modifier(int baseStat){
        int mod = (int)(baseStat - 10) /2;
        if (mod < 0) {
            mod--;
        }
        return mod;
    }
}
