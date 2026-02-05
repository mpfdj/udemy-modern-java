package jaeger.de.miel;

import jaeger.de.miel.sealed.Car;
import jaeger.de.miel.sealed.Truck;
import jaeger.de.miel.sealed.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class SealedClassesTest {

    @Test
    public void testWithSealedClasses() {

        var car = new Car();
        var truck = new Truck();

        assertInstanceOf(Vehicle.class, car);
        assertInstanceOf(Vehicle.class, truck);

    }

    @Test
    public void testWithAbstractMethods() {
        var car = new Car();
        var truck = new Truck();

        assertInstanceOf(Vehicle.class, car);
        assertInstanceOf(Vehicle.class, truck);

        assertEquals("CAR", car.drive());
        assertEquals("TRUCK", truck.drive());

    }

}
