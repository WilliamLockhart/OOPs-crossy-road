package entity.player;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import graphics.Assets;

public class OrientationAnimator {
    Image forwardRight_IMG;
    Image forwardLeft_IMG;
    Image backRight_IMG;
    Image backLeft_IMG;

    public enum PlayerOrientation{
        FORWARD_LEFT,
        FORWARD_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    PlayerOrientation playerOrientation;

    private void loadCharacterImages() {
        //these values are for the character.png, the have to be hard coded somewhere, and i didnt want to deal with json just for this
        int forwardRightX = 16,  forwardRightY = 149, forwardRightW = 14, forwardRightH = 17;
        int backRightX = 49,  backRightY = 149, backRightW = 13, backRightH = 17;
        int forwardLeftX = 113, forwardLeftY = 149, forwardLeftW = 14, forwardLeftH = 17;  
        int backLeftX = 81,  backLeftY = 149, backLeftW = 13, backLeftH = 17; 

        Image sheet = Assets.getInstance().loadImage("Character.png");
        PixelReader reader = sheet.getPixelReader();

        forwardRight_IMG = new WritableImage(reader, forwardRightX, forwardRightY, forwardRightW, forwardRightH);
        forwardLeft_IMG  = new WritableImage(reader, forwardLeftX, forwardLeftY, forwardLeftW, forwardLeftH);
        backRight_IMG    = new WritableImage(reader, backRightX, backRightY, backRightW, backRightH);
        backLeft_IMG     = new WritableImage(reader, backLeftX, backLeftY, backLeftW, backLeftH);
    }

    public OrientationAnimator() {
        loadCharacterImages();}

    public Image getImage(PlayerOrientation orientation) {
        switch(orientation) {
            case FORWARD_RIGHT: return forwardRight_IMG;
            case FORWARD_LEFT:  return forwardLeft_IMG;
            case BACK_LEFT:     return backLeft_IMG;
            case BACK_RIGHT:    return backRight_IMG;
            default: return forwardRight_IMG;}
    }
}