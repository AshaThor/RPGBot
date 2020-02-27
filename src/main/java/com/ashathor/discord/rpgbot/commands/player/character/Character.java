package com.ashathor.discord.rpgbot.commands.player.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class Character {
    private Long playerId;
    private String characterName;
    private String characterClass;
    private Integer characterLevel;
    private String characterBackground;
    private String characterRace;
    private String characterAlignment;
    private Integer characterExp;

    private Integer characterStrength;
    private Integer characterDexterity;
    private Integer characterConstitution;
    private Integer characterIntelligence;
    private Integer characterWisdom;
    private Integer characterCharisma;

    private Integer characterInspiration;
    private Integer characterProfBonus;

    private boolean characterSaveStrength;
    private boolean characterSaveDexterity;
    private boolean characterSaveConstitution;
    private boolean characterSaveIntelligence;
    private boolean characterSaveWisdom;
    private boolean characterSaveCharisma;

    private boolean characterSkillAcrobatics;
    private boolean characterSkillAnimalHandling;
    private boolean characterSkillArcana;
    private boolean characterSkillAthletics;
    private boolean characterSkillDeception;
    private boolean characterSkillHistory;
    private boolean characterSkillInsight;
    private boolean characterSkillIntimidation;
    private boolean characterSkillInvestigation;
    private boolean characterSkillMedicine;
    private boolean characterSkillNature;
    private boolean characterSkillPerception;
    private boolean characterSkillPerformance;
    private boolean characterSkillReligion;
    private boolean characterSkillSleightOfHand;
    private boolean characterSkillStealth;
    private boolean characterSkillSurvival;

    public Character(Long playerId) {
        this.playerId = playerId;
    }
}
