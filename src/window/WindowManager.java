package window;

import javafx.application.Application;
import javafx.stage.Stage;



public class WindowManager extends Application {
    private Window window;
    private WindowScene windowScene;

    public static final double WINDOW_WIDTH = 1280;
     public static final double WINDOW_HEIGHT = 720;

    @Override
    public void start(Stage stage) {
        window = new Window(stage);
        windowScene = new WindowScene(WINDOW_WIDTH, WINDOW_HEIGHT);
        setWindowDimensions(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setScene(windowScene.getScene());
        window.showStage();
    }

    public void setWindowDimensions(double width, double height){
        if(width <= 1)
            width =1;
        if(height <= 1)
            height =1;

        window.setDimensions(width, height);
    }

    public WindowScene getWindowSceneObject() {
        return windowScene;
    }

}
