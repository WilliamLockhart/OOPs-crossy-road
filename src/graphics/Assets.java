package graphics;
import javafx.scene.image.Image;

public class Assets {
    static public Image loadImage(String fileName) {
        return new Image("file:assets/" + fileName);
    }

    static public Image loadImage(String fileName, double width, double height) {
        return new Image("file:assets/" + fileName, width, height, false, false);
    }
}
