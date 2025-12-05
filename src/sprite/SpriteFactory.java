package sprite;

import javafx.scene.image.Image;
import graphics.Assets;

public class SpriteFactory {

    public static Sprite generateSprite(String entityName,
                                        String imageFilename,
                                        double x, double y,
                                        double w, double h) {

        Position position = new Position(x, y, w, h);
        Image spriteImage;

        // Use cached images
        switch (imageFilename) {
            case "Red_Truck.png":
                spriteImage = Assets.TRUCK_RIGHT_IMG;
                break;

            case "Green_Truck.png":
                spriteImage = Assets.TRUCK_LEFT_IMG;
                break;

            case "Purple_car.png":
                spriteImage = Assets.CAR_RIGHT_IMG;
                break;

            case "Orange_car.png":
                spriteImage = Assets.CAR_LEFT_IMG;
                break;

            default:
                // fallback: load normally (non-cached)
                spriteImage = Assets.loadImage(imageFilename);
                break;
        }

        return new Sprite(entityName, spriteImage, position);
    }
}
