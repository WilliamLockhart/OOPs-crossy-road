package entity.player;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class Input {

    private final Set<KeyCode> keysDown = new HashSet<>();

    public Input(Scene scene) {
        scene.setOnKeyPressed(e -> keysDown.add(e.getCode()));
        scene.setOnKeyReleased(e -> keysDown.remove(e.getCode()));
    }

    public boolean isKeyDown(KeyCode key) {
        return keysDown.contains(key);
    }
    
    public Set<KeyCode> getKeysDown() {
        return keysDown;
    }
}