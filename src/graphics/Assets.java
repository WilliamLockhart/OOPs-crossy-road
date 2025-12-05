package graphics;

import javafx.scene.image.Image;

public class Assets {

    private static final String BASE = "file:assets/";
    private static final Assets INSTANCE = new Assets();

    private final Image truckRightImg;
    private final Image truckLeftImg;
    private final Image carLeftImg;
    private final Image carRightImg;

    private Assets() {
        this.truckRightImg = new Image(BASE + "Red_Truck.png");
        this.truckLeftImg  = new Image(BASE + "Green_Truck.png");
        this.carLeftImg    = new Image(BASE + "Orange_car.png");
        this.carRightImg   = new Image(BASE + "Purple_car.png");
    }

    public static Assets getInstance() {return INSTANCE;}

    public Image getTruckRight() { return truckRightImg; }
    public Image getTruckLeft()  { return truckLeftImg; }
    public Image getCarLeft()    { return carLeftImg; }
    public Image getCarRight()   { return carRightImg; }

    public Image loadImage(String fileName) {return new Image(BASE + fileName);}

    public Image loadImage(String fileName, double width, double height) {
        return new Image(BASE + fileName, width, height, false, false);}
}
