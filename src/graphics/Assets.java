package graphics;

import javafx.scene.image.Image;

public class Assets {

    // All images are loaded from a folder named "assets" in the project root.
    private static final String BASE = "file:assets/";

    public static final Image TRUCK_RIGHT_IMG =
        new Image(BASE + "Red_Truck.png");

    public static final Image TRUCK_LEFT_IMG =
        new Image(BASE + "Green_Truck.png");

    public static final Image CAR_LEFT_IMG =
        new Image(BASE + "Orange_car.png");

    public static final Image CAR_RIGHT_IMG =
        new Image(BASE + "Purple_car.png");

    // Fallback loaders for anything else
    public static Image loadImage(String fileName) {
        return new Image(BASE + fileName);
    }

    public static Image loadImage(String fileName, double width, double height) {
        return new Image(BASE + fileName, width, height, false, false);
    }
}
