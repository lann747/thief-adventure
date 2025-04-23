import greenfoot.*;

public class MusicManager {
    private static GreenfootSound bgm = new GreenfootSound("main.mp3");

    public static void playLoop() {
        if (!bgm.isPlaying()) {
            bgm.setVolume(50);
            bgm.playLoop();
        }
    }

    public static void stop() {
        if (bgm.isPlaying()) {
            bgm.stop();
        }
    }
}
