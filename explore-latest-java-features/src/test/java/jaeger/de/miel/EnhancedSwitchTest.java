package jaeger.de.miel;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Month;
import java.time.Year;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnhancedSwitchTest {

    private static final int CURRENT_YEAR = 2026;


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


// ----------------------------------------------
// Test cases enhanced switch statement
// ----------------------------------------------

    @ParameterizedTest(name = "Testing month {0}")
    @EnumSource(Month.class)
    public void testDaysInMonthOldSkool(Month month) {
        var days = getDaysOldSkool(month, CURRENT_YEAR);

        switch (month) {
            case FEBRUARY -> assertEquals(29, days);
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> assertEquals(30, days);
            default -> assertEquals(31, days);
        }
    }

    @ParameterizedTest(name = "Testing month {0}")
    @EnumSource(Month.class)
    public void testDaysInMonthNewSkool(Month month) {
        var days = getDaysNewSkool(month, CURRENT_YEAR);

        switch (month) {
            case FEBRUARY -> assertEquals(29, days);
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> assertEquals(30, days);
            default -> assertEquals(31, days);
        }
    }


// ----------------------------------------------
// Enhanced switch
// ----------------------------------------------

    public int getDaysOldSkool(Month month, int year) {
        int noOfDays = 0;
        switch (month) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                noOfDays = 30;
                break;  //return noOfDays
            case FEBRUARY: // this is a code block
                System.out.println("Checking if " + year + " is a leap year");
                noOfDays = Year.isLeap(year) ? 29 : 28;
                break;  //  return noOfDays
            default:
                noOfDays = 31;
        }
        return noOfDays;
    }

    public int getDaysNewSkool(Month month, int year) {
        return switch (month) {
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> {  // this is a code block (lambda expression)
                System.out.println("Checking if " + year + " is a leap year");
                yield Year.isLeap(year) ? 29 : 28;
            }
            default -> 31;
        };
    }

}
