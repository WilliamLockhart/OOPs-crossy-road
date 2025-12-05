package sprite;

import javafx.scene.image.Image;
import graphics.Assets;

public class SpriteFactory {
    public static Sprite generateSprite(String entityName, String imageFilename, double x, double y, double w, double h){
        Position position = new Position(x, y, w, h);
        Image spriteImage = Assets.loadImage(imageFilename);
        return new Sprite(entityName, spriteImage, position); }
}