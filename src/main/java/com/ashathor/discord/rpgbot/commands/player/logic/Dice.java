package com.ashathor.discord.rpgbot.commands.player.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class Dice{

    private static final Logger logger = LogManager.getLogger(Dice.class);

    /**
     * Rolling logic
     * Simulates rolling a dice for a given number of a type of dice
     * @param volume - How many dice you want to roll
     * @param sides - How many sides the dice should have
     * */
    public ArrayList<Integer> roll(int volume, int sides){
        //Make list of rolls to send to the player
        ArrayList<Integer> roles = new ArrayList<Integer>(volume);
        logger.info("Array of rolls created");
        for(int count = 0; count < volume; count++) {
            roles.add((int)(Math.random() * sides) + 1);
        }
        logger.info("Rolling success");
        sort(roles);
        logger.info("Rolls sorted");
        return roles;
    }
    /**
     * Sorting the array of dice rolls into accending order
     * @param roles ArrayList of the roles
     * */
    private ArrayList<Integer> sort(ArrayList<Integer> roles){
        Collections.sort(roles);
        return roles;
    }
    @Override
    public String toString() {
        return "Dice{}";
    }
}
