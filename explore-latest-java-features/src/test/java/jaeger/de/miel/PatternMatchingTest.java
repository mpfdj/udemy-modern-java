package jaeger.de.miel;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;



// Type patterns
// Record patterns
// Guarded patterns

public class PatternMatchingTest {


    @ParameterizedTest
    @MethodSource("input")
    public void testPatternMatchingOldSkool(Object o, String expectedResult) {
        var result = patternMatchingOldSkool(o);
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("input")
    public void testPatternMatchingUsingInstanceOf(Object o, String expectedResult) {
        var result = patternMatchingUsingInstanceOf(o);
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("input")
    public void testPatternMatchingUsingEnhanceSwitch(Object o, String expectedResult) {
        var result = patternMatchingUsingEnhancedSwitch(o);
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of("Dilip", "String of length: 5"),
                Arguments.of(1, "Integer: 1"),
                Arguments.of(null, "Not a String or Integer")
        );
    }


// ----------------------------------------------
// Pattern Matching examples
// ----------------------------------------------

    public String patternMatchingOldSkool(Object o) {
        if (o instanceof Integer) {
            var i = (Integer) o;
            return "Integer: " + i;
        }
        if (o instanceof String) {
            var s = (String) o;
            return "String of length: " + s.length();

        }
        return "Not a String or Integer";
    }

    // patternMatching using InstanceOf - Java 16
    public String patternMatchingUsingInstanceOf(Object o) {
        if (o instanceof Integer i) {
            return "Integer: " + i;
        }
        if (o instanceof String s) {
            return "String of length: " + s.length();

        }
        return "Not a String or Integer";
    }

    // patternMatching using Switch - Java 21
    public String patternMatchingUsingEnhancedSwitch(Object o) {
        return switch (o) {
            case String s -> "String of length: " + s.length();
            case Integer i -> "Integer: " + i;
            case null, default -> "Not a String or Integer";
        };
    }
}
