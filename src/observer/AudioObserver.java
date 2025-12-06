package observer;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioObserver implements Observer {

    private final Map<String, Clip> sounds = new HashMap<>();

    public AudioObserver() throws Exception {
        sounds.put("Car honk!",  loadClip("assets/car_horn.wav"));
        sounds.put("Truck horn!", loadClip("assets/truck_horn.wav"));
    }

    private Clip loadClip(String path) throws Exception {
        File file = new File(path);
        if (!file.exists())
            throw new IllegalArgumentException("Missing audio file: " + file.getAbsolutePath());

        AudioInputStream in = AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();
        clip.open(in);

        return clip;
    }

    @Override
    public void update(String eventDescription) {
        Clip clip = sounds.get(eventDescription);
        if (clip != null)
            play(clip);
    }

    private void play(Clip clip) {
        if (clip.isRunning())
            clip.stop();

        clip.setFramePosition(0);
        clip.start();
    }
}
