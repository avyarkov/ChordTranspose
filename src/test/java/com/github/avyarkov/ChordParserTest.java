package com.github.avyarkov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChordParserTest {

    @Test
    void printAllNotesModifiersAndChords() {
        System.out.println(ChordParser.VALID_NOTE_STRINGS);
        System.out.println(ChordParser.VALID_MODIFIER_STRINGS);
        System.out.println(ChordParser.VALID_CHORD_STRINGS);
        assertEquals(ChordParser.VALID_CHORD_STRINGS.size(),
            ChordParser.VALID_NOTE_STRINGS.size() * ChordParser.VALID_MODIFIER_STRINGS.size());
    }

    @Test
    void transposeOneUp() {
        var transposingMap = ChordParser.transposingMap(1);
        assertEquals(transposingMap.get("A"), "A#");
        assertEquals(transposingMap.get("Bm"), "Cm");
        assertEquals(transposingMap.get("E5"), "F5");
        assertEquals(transposingMap.get("G#7"), "A7");
        assertEquals(transposingMap.get("Hm7"), "Cm7");
    }
}