package jaeger.de.miel.sealed;

public sealed interface SmartMediaPlayer permits Car {

    void connectPhone();
    void playMusic();
    void stopMusic();

}
