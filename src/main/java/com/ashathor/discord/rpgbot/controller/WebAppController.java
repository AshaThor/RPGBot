package com.ashathor.discord.rpgbot.controller;

import com.ashathor.discord.rpgbot.commands.player.character.Character;
import com.ashathor.discord.rpgbot.util.json.JsonEntityConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class WebAppController {
    private String appMode;

    @Autowired
    public JsonEntityConversion jsonEntityConversion;

    @Autowired
    public WebAppController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model) throws IOException {
        try {
            Character playerChar = jsonEntityConversion.read("c:\\dnd\\103635956707381248.json");
            model.addAttribute("username", "AshaThor");
            model.addAttribute("mode", appMode);
            model.addAttribute("characterName", playerChar.getCharacterName());
            model.addAttribute("characterRace", playerChar.getCharacterRace());
            return "index";
        }catch (IOException e){
            return null;
        }
    }
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}