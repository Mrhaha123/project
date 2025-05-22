# String Processor

A Java library that processes strings by removing or replacing consecutive identical characters based on specific rules.

## Features

1. **Removal Strategy**: Removes 3 or more consecutive identical characters from a string
2. **Replacement Strategy**: Replaces 3 or more consecutive identical characters with the previous character in the alphabet

## Requirements

- Java 11 or above
- Maven 3.6 or above

## Usage

```java
// Using Removal Strategy
StringProcessor removalProcessor = new StringProcessor(new StringProcessor.RemovalStrategy());
String result1 = removalProcessor.process("aabcccbbad"); // Returns "d"

// Using Replacement Strategy
StringProcessor replacementProcessor = new StringProcessor(new StringProcessor.ReplacementStrategy());
String result2 = replacementProcessor.process("abcccbad"); // Returns "d"
```

## Examples

### Removal Strategy
Input: "aabcccbbad"
Output:
- "aabbbad" (removed "ccc")
- "aaad" (removed "bbb")
- "d" (removed "aaa")

### Replacement Strategy
Input: "abcccbad"
Output:
- "abbbad" (replaced "ccc" with "b")
- "aaad" (replaced "bbb" with "a")
- "d" (replaced "aaa" with "")

## Building and Testing

To build the project:
```bash
mvn clean install
```

To run tests:
```bash
mvn test
```

## Design Patterns Used

1. **Strategy Pattern**: Used to implement different string processing strategies (removal and replacement)
2. **Single Responsibility Principle**: Each strategy class has a single responsibility
3. **Open/Closed Principle**: New strategies can be added without modifying existing code

## Java Features Used

1. Java 11 features:
   - String.repeat() method
   - Local variable type inference
2. Modern Java features:
   - Lambda expressions
   - Stream API
   - Parameterized tests with JUnit 5 