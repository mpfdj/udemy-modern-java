package jaeger.de.miel.patternmatching.record;

public class AnimalService {

    public String retrieveName(Animal a) {
        return switch (a) {
            case Cat c when c.name() == null -> "no name";  // A guarded pattern is also applicable for enhanced switch
            case Cat c -> c.name();
            case Dog d -> d.name();
            case null -> "";
        };
    }

    // Enhanced switch with Record Pattern (Record pattern)
    public String retrieveNameV2(Animal a) {
        return switch (a) {
            case Cat(var name, var color) -> name;
            case Dog(var name, var color) -> name;
            case null -> "";  // The compiler doesn't require a default case because we are using a sealed interface
        };
    }

    // Enhanced switch with a Guarded Record pattern (Guarded pattern)
    public String retrieveNameV3(Animal a) {
        return switch (a) {
            case Cat(var name, var color) when name == null -> "";  // Guarded pattern is an extra check
            case Cat(var name, var color) -> name;
            case Dog(var name, var color) -> name;
            case null -> "";  // The compiler doesn't require a default case because we are using a sealed interface
        };
    }
}
