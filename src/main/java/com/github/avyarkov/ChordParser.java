package com.github.avyarkov;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class ChordParser {
    private static final int TOTAL_NUMBER_OF_NOTES = 12;
    private static int noteTransposedBy(int noteId, int numberOfSteps) {
        return ((noteId + numberOfSteps) % TOTAL_NUMBER_OF_NOTES + TOTAL_NUMBER_OF_NOTES) % TOTAL_NUMBER_OF_NOTES;
    }

    private static final Map<String, Integer> STRING_TO_NOTE_ID = Map.ofEntries(
        entry("C", 0),
        entry("C#", 1),
        entry("Db", 1),
        entry("D", 2),
        entry("D#", 3),
        entry("Eb", 3),
        entry("E", 4),
        entry("F", 5),
        entry("F#", 6),
        entry("Gb", 6),
        entry("G", 7),
        entry("G#", 8),
        entry("Ab", 8),
        entry("A", 9),
        entry("A#", 10),
        entry("Bb", 10),
        entry("Hb", 10),
        entry("B", 11),
        entry("H", 11)
    );

    public static final List<String> VALID_NOTE_STRINGS = List.copyOf(STRING_TO_NOTE_ID.keySet()
        .stream().sorted().collect(Collectors.toList()));

    private static final Map<Integer, String> NOTE_ID_TO_STRING = Map.ofEntries(
        entry(0, "C"),
        entry(1, "C#"),
        entry(2, "D"),
        entry(3, "D#"),
        entry(4, "E"),
        entry(5, "F"),
        entry(6, "F#"),
        entry(7, "G"),
        entry(8, "G#"),
        entry(9, "A"),
        entry(10, "A#"),
        entry(11, "B")
    );

    private static final Map<String, Integer> STRING_TO_MODIFIER_ID = Map.ofEntries(
        entry("", 0),
        entry("m", 1),
        entry("5", 2),
        entry("7", 3),
        entry("m7", 4),
        entry("maj7", 5)
        );

    public static final List<String> VALID_MODIFIER_STRINGS = List.copyOf(STRING_TO_MODIFIER_ID.keySet()
        .stream().sorted().collect(Collectors.toList()));

    // for reference and testing
    public static final List<String> VALID_CHORD_STRINGS = List.copyOf(
        VALID_NOTE_STRINGS.stream().flatMap(s -> VALID_MODIFIER_STRINGS.stream().map(t -> s + t))
            .collect(Collectors.toList())
    );

    static Map<String, String> transposingMap(int numberOfSteps) {
        HashMap<String, String> result = new HashMap<>();
        for (String noteString : VALID_NOTE_STRINGS) {
            for (String modifierString : VALID_MODIFIER_STRINGS) {
                String chordString = noteString + modifierString;
                int transposedNote = noteTransposedBy(STRING_TO_NOTE_ID.get(noteString), numberOfSteps);
                String transposedChordString = NOTE_ID_TO_STRING.get(transposedNote) + modifierString;
                result.put(chordString, transposedChordString);
            }
        }
        return result;
    }

    public static String transposeStringBy(String input, int numberOfSteps) {
        return TextParser.replaceWords(input, transposingMap(numberOfSteps));
    }

}
