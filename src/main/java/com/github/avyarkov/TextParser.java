package com.github.avyarkov;

import java.util.Map;

public class TextParser {

    static String replaceWords(String inputText, Map<String, String> replacements) {
        String[] words = inputText.split("\\b");
        for (int i = 0; i < words.length; i++) {
            words[i] = replacements.getOrDefault(words[i], words[i]);
        }
        return String.join("", words);
    }

}
