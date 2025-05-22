package com.stringprocessor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringProcessorTest {

    @Test
    void testRemovalStrategy() {
        StringProcessor processor = new StringProcessor(new StringProcessor.RemovalStrategy());
        
        assertEquals("d", processor.process("aabcccbbad"));
        assertEquals("aabbbad", processor.process("aabcccbbad"));
        assertEquals("aaad", processor.process("aabbbad"));
    }

    @Test
    void testReplacementStrategy() {
        StringProcessor processor = new StringProcessor(new StringProcessor.ReplacementStrategy());
        
        assertEquals("d", processor.process("abcccbad"));
        assertEquals("abbbad", processor.process("abcccbad"));
        assertEquals("aaad", processor.process("abbbad"));
    }

    @ParameterizedTest
    @CsvSource({
        "aabcccbbad, d",
        "abcccbad, d",
        "aaabbb, ''",
        "aaa, ''",
        "aa, aa",
        "a, a"
    })
    void testRemovalStrategyWithVariousInputs(String input, String expected) {
        StringProcessor processor = new StringProcessor(new StringProcessor.RemovalStrategy());
        assertEquals(expected, processor.process(input));
    }

    @ParameterizedTest
    @CsvSource({
        "aabcccbbad, d",
        "abcccbad, d",
        "aaabbb, ''",
        "aaa, ''",
        "aa, aa",
        "a, a"
    })
    void testReplacementStrategyWithVariousInputs(String input, String expected) {
        StringProcessor processor = new StringProcessor(new StringProcessor.ReplacementStrategy());
        assertEquals(expected, processor.process(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testNullAndEmptyInput(String input) {
        StringProcessor removalProcessor = new StringProcessor(new StringProcessor.RemovalStrategy());
        StringProcessor replacementProcessor = new StringProcessor(new StringProcessor.ReplacementStrategy());
        
        assertEquals(input, removalProcessor.process(input));
        assertEquals(input, replacementProcessor.process(input));
    }

    @Test
    void testEdgeCases() {
        StringProcessor removalProcessor = new StringProcessor(new StringProcessor.RemovalStrategy());
        StringProcessor replacementProcessor = new StringProcessor(new StringProcessor.ReplacementStrategy());
        
        // Test with single character
        assertEquals("a", removalProcessor.process("a"));
        assertEquals("a", replacementProcessor.process("a"));
        
        // Test with two identical characters
        assertEquals("aa", removalProcessor.process("aa"));
        assertEquals("aa", replacementProcessor.process("aa"));
        
        // Test with three identical characters
        assertEquals("", removalProcessor.process("aaa"));
        assertEquals("", replacementProcessor.process("aaa"));
        
        // Test with mixed cases
        assertEquals("abc", removalProcessor.process("abc"));
        assertEquals("abc", replacementProcessor.process("abc"));
    }
} 