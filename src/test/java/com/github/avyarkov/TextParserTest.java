package com.github.avyarkov;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

    @Test
    void readAndReplaceWithQuestionMarks() {
        String input;
        try {
            input = new String(Files.readAllBytes(Paths.get("src/test/resources/Lyrics.txt")));
        } catch (IOException e) {
            throw new RuntimeException("File not found!", e);
        }
        String text = TextParser.replaceWords(input, Map.of("Em", "??"));
        System.out.println(text);
    }

    @Test
    void replaceWordsGivenImmutableMap() {
        String input = "a b   c l'b b\nb b\tb sdf|b";
        Map<String, String> map = Map.of("b", "?");
        String output = TextParser.replaceWords(input, map);
        assertEquals(output, "a ?   c l'? ?\n? ?\t? sdf|?");
    }
}