package window;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window {
    private Stage stage;

    public Window(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("OOPS We Crossed the Road");
        this.stage.setResizable(false);
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public void showStage() {
        stage.show();
    }

    public Scene getWindowScene() {
        return stage.getScene();
    }

    public void setDimensions(double width, double height){
        if(width <= 1)
            width =1;
        if(height <= 1)
            height =1;

        stage.setWidth(width);
        stage.setHeight(height);
    }
}
