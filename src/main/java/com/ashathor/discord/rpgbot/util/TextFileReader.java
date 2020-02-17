package com.ashathor.discord.rpgbot.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class TextFileReader {
    public BufferedReader read(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }
}
