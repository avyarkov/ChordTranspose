package com.github.avyarkov;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TextParser {
    // Sharp signs are considered punctuation in regex and therefore are word boundaries, but shouldn't be!
    // My solution: Replace all sharp signs with arbitrary alphanumeric symbol "ᶑ".
    // Interesting wiki fact: It is used in phonetic transcription to represent a voiced retroflex implosive.
    // It is not known to be phonemically distinct from alveolar /ɗ/ in any language, which means it doesn't actually exist.
    // As this sound doesn't actually exist in reality, I really hope nobody uses it a text.
    // If anyone does, and it messes up the algorithm... I'm sorry :(
    private static final String sharpSign = "#";
    private static final String arbitraryAlphanumericSymbol = "ᶑ";

    private static final Pattern sharpSignPattern = Pattern.compile(sharpSign);
    private static final Pattern arbitraryAlphanumericSymbolPattern = Pattern.compile(arbitraryAlphanumericSymbol);

    private static String swapForwards(String input) {
        return sharpSignPattern.matcher(input).replaceAll(arbitraryAlphanumericSymbol);
    }
    private static String swapBack(String input) {
        return arbitraryAlphanumericSymbolPattern.matcher(input).replaceAll(sharpSign);
    }

    static String replaceWords(String inputText, Map<String, String> replacements) {
        String modifiedInputText = swapForwards(inputText);
        Map<String, String> modifiedReplacements = new HashMap<>();
        replacements.forEach((k, v) -> modifiedReplacements.put(swapForwards(k), swapForwards(v)));

        String[] words = modifiedInputText.split("\\b");
        for (int i = 0; i < words.length; i++) {
            words[i] = modifiedReplacements.getOrDefault(words[i], words[i]);
        }
        String output = String.join("", words);
        return swapBack(output);
    }

}