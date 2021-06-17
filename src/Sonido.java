import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sonido {

    static Sistema sistema = new Sistema();

    public static void plin() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File("Sounds" + sistema.getBarra() + "plin.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(plin);
        clip.start();
    }

    public static void cachin() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File("Sounds" + sistema.getBarra() + "cachin.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(plin);
        clip.start();
    }

    public static void error() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File("Sounds" + sistema.getBarra() + "error.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(plin);
        clip.start();
    }
}
