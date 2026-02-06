package jaeger.de.miel;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Month;
import java.util.stream.Stream;

public class ParameterizedTestExamples {

    // ----------------------------------------------
    // Some ParameterizedTest cases
    // ----------------------------------------------

    @ParameterizedTest(name = "Testing month {0}")
    @EnumSource(Month.class)
    public void testWithEnumSource(Month month) {
        System.out.println("Month is: " + month);
    }

    @ParameterizedTest(name = "Testing month {0}")
    @MethodSource("input")
    public void testWithMethodSource(Month month) {
        System.out.println("Month is: " + month);
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(Month.JANUARY),
                Arguments.of(Month.FEBRUARY),
                Arguments.of(Month.APRIL));
    }

}
