package window;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class WindowScene {
    private Scene scene;
    private Pane rootPane;

    public WindowScene(double width, double height) {
        rootPane = new Pane();
        this.scene = new Scene(rootPane, width, height);
    }

    public Scene getScene() {
        return scene;
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
