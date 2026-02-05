package jaeger.de.miel;

import jaeger.de.miel.patternmatching.record.Animal;
import jaeger.de.miel.patternmatching.record.AnimalService;
import jaeger.de.miel.patternmatching.record.Cat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternMatchingRecordsTest {

    private final AnimalService animalService = new AnimalService();


    @ParameterizedTest
    @MethodSource("input")
    public void testPatternMatching(Animal a, String expected) {
        var name = animalService.retrieveName(a);
        assertEquals(expected, name);
    }

    @ParameterizedTest
    @MethodSource("input")
    public void testPatternMatchingWithRecordMatching(Animal a, String expected) {
        var name = animalService.retrieveNameV2(a);
        assertEquals(expected, name);
    }

    @Test
    public void testPatternMatchingWithGuardedPattern() {
        var cat = new Cat(null, "black");
        var name = animalService.retrieveName(cat);
        assertEquals("no name", name);
    }

    @Test
    public void testPatternMatchingWithGuardedRecordPattern() {
        var c = new Cat(null, "black");
        var name = animalService.retrieveNameV3(c);
        assertEquals("", name);
    }


    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(new Cat("Kitty", "black"), "Kitty"),
                Arguments.of(new Cat("Scooby", "brown"), "Scooby"),
                Arguments.of(null, "")
        );
    }

}
