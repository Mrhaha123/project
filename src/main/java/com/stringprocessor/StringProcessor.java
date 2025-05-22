package com.stringprocessor;

import java.util.function.Function;

public class StringProcessor {
    private final ProcessingStrategy strategy;

    public StringProcessor(ProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public String process(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String result = input;
        String previousResult;
        
        do {
            previousResult = result;
            result = strategy.process(result);
        } while (!result.equals(previousResult));

        return result;
    }

    public interface ProcessingStrategy {
        String process(String input);
    }

    public static class RemovalStrategy implements ProcessingStrategy {
        @Override
        public String process(String input) {
            StringBuilder result = new StringBuilder();
            int i = 0;
            
            while (i < input.length()) {
                char currentChar = input.charAt(i);
                int count = 1;
                
                while (i + count < input.length() && input.charAt(i + count) == currentChar) {
                    count++;
                }
                
                if (count < 3) {
                    result.append(String.valueOf(currentChar).repeat(count));
                }
                
                i += count;
            }
            
            return result.toString();
        }
    }

    public static class ReplacementStrategy implements ProcessingStrategy {
        @Override
        public String process(String input) {
            StringBuilder result = new StringBuilder();
            int i = 0;
            
            while (i < input.length()) {
                char currentChar = input.charAt(i);
                int count = 1;
                
                while (i + count < input.length() && input.charAt(i + count) == currentChar) {
                    count++;
                }
                
                if (count >= 3) {
                    char replacementChar = (char) (currentChar - 1);
                    if (replacementChar >= 'a') {
                        result.append(replacementChar);
                    }
                } else {
                    result.append(String.valueOf(currentChar).repeat(count));
                }
                
                i += count;
            }
            
            return result.toString();
        }
    }
} 