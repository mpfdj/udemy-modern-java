package jaeger.de.miel.sealed;

public sealed class Car extends Vehicle implements SmartMediaPlayer permits FlyingCar {

    @Override
    public String drive() {
        return "CAR";
    }

    @Override
    public void connectPhone() {
        System.out.println("connectPhone");
    }

    @Override
    public void playMusic() {
        System.out.println("playMusic");
    }

    @Override
    public void stopMusic() {
        System.out.println("stopMusic");
    }
}
