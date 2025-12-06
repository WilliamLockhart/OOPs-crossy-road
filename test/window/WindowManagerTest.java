package window;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WindowManagerTest {

    @Test
    void windowConstantsAreCorrect() {
        assertEquals(1280.0, WindowManager.WINDOW_WIDTH);
        assertEquals(720.0, WindowManager.WINDOW_HEIGHT);
    }
}
