package com.github.avyarkov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChordParserTest {

    @Test
    void printAllNotesModifiersAndChords() {
        System.out.println(ChordParser.VALID_NOTE_STRINGS);
        System.out.println(ChordParser.VALID_MODIFIER_STRINGS);
        System.out.println(ChordParser.VALID_CHORD_STRINGS);
        assertEquals(ChordParser.VALID_NOTE_STRINGS.size() * ChordParser.VALID_MODIFIER_STRINGS.size(),
            ChordParser.VALID_CHORD_STRINGS.size());
    }

    @Test
    void mapOneUp() {
        var transposingMap = ChordParser.transposingMap(1);
        assertEquals("A#", transposingMap.get("A"));
        assertEquals("Cm", transposingMap.get("Bm"));
        assertEquals("F5", transposingMap.get("E5"));
        assertEquals("A7", transposingMap.get("G#7"));
        assertEquals("Cm7", transposingMap.get("Hm7"));
        assertEquals("Dmaj7", transposingMap.get("Dbmaj7"));
    }

    @Test
    void mapOneDown() {
        var transposingMap = ChordParser.transposingMap(-1);
        assertEquals("G#", transposingMap.get("A"));
        assertEquals("Bm", transposingMap.get("Cm"));
        assertEquals("E5", transposingMap.get("F5"));
        assertEquals("G7", transposingMap.get("G#7"));
        assertEquals("A#m7", transposingMap.get("Hm7"));
        assertEquals("Cmaj7", transposingMap.get("Dbmaj7"));
    }

    @Test
    void transposeAllMajorChordsByOneUp() {
        String input = " A A# Ab  B   Bb C C# D D# Db E\tEb F F# G G# Gb H Hb  ";
        String expectedOutput = " A# B A  C   B C# D D# E D F\tE F# G G# A G C B  ";
        String output = ChordParser.transposeStringBy(input, 1);
        assertEquals(expectedOutput, output);
    }

    @Test
    void transposeAllMajorChordsByOneDown() {
        String input = " A A# Ab  B   Bb C C# D D# Db E\tEb F F# G G# Gb H Hb  ";
        String expectedOutput = " G# A G  A#   A B C C# D C D#\tD E F F# G F A# A  ";
        String output = ChordParser.transposeStringBy(input, -1);
        assertEquals(expectedOutput, output);
    }

    @Test
    void transposeAllModifiers() {
        String input = "C Cm C5 C7 Cm7 Cmaj7";
        String expectedOutput = "D Dm D5 D7 Dm7 Dmaj7";
        String output = ChordParser.transposeStringBy(input, 2);
        assertEquals(expectedOutput, output);
    }

    @Test
    void transposeSharpedPlusAllModifiers() {
        String input = "C# C#m C#5 C#7 C#m7 C#maj7";
        String expectedOutput = "D Dm D5 D7 Dm7 Dmaj7";
        String output = ChordParser.transposeStringBy(input, 1);
        assertEquals(expectedOutput, output);
    }
}