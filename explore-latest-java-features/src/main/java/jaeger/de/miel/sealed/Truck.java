package jaeger.de.miel.sealed;

//public final class Truck extends Vehicle {
//public non-sealed class Truck extends Vehicle implements SmartMediaPlayer {  // Not allowed because SmartMediaPlayer interface is sealed
public non-sealed class Truck extends Vehicle {

    @Override
    public String drive() {
        return "TRUCK";
    }

}
