package sprite;

import javafx.scene.image.Image;
import graphics.Assets;

public class SpriteFactory {

    public static Sprite generateSprite(String imageFilename,
                                        double x, double y,
                                        double w, double h) {

        Position position = new Position(x, y, w, h);
        Image spriteImage;

        // Use cached images or memory runs out :(
        switch (imageFilename) {
            case "Red_Truck.png":
                spriteImage = Assets.getInstance().getTruckRight();
                break;

            case "Green_Truck.png":
                spriteImage = Assets.getInstance().getTruckLeft();
                break;

            case "Purple_car.png":
                spriteImage = Assets.getInstance().getCarRight();
                break;

            case "Orange_car.png":
                spriteImage = Assets.getInstance().getCarLeft();
                break;

            default:
                // fallback: load normally (non-cached)
                spriteImage = Assets.getInstance().loadImage(imageFilename);
                break;
        }

        return new Sprite(spriteImage, position);
    }
}
