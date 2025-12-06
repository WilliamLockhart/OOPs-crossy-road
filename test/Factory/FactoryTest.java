package Factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sprite.*;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

public class FactoryTest {

    @BeforeAll
    static void initJavaFx() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
        }
    }

    @Test
    void testSpriteCreation() {
        Sprite sprite = SpriteFactory.generateSprite("duck.png", 100,100,100,100);
        assertNotNull(sprite);
        assertNotNull(sprite.getSpriteImage());
    }
}
