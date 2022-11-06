package com.github.avyarkov;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class ChordParser {
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

    static final Set<String> VALID_NOTE_STRINGS = Set.copyOf(STRING_TO_NOTE_ID.keySet());

    private static final Map<String, Integer> STRING_TO_MODIFIER_ID = Map.ofEntries(
        entry("", 0),
        entry("m", 1),
        entry("7", 2),
        entry("m7", 3),
        entry("5", 4)
    );

    static final Set<String> VALID_MODIFIER_STRINGS = Set.copyOf(STRING_TO_MODIFIER_ID.keySet());

    static final Set<String> VALID_CHORD_STRINGS = Set.copyOf(
        VALID_NOTE_STRINGS.stream().flatMap(s -> VALID_MODIFIER_STRINGS.stream().map(t -> s + t))
            .collect(Collectors.toSet())
    );

}
