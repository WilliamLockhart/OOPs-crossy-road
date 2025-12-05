package sprite;
import javafx.scene.image.Image;
import sprite.HitBox.*;
public class Sprite {
    private Image entityIMG;
    private Position position;
    private HitBox hitBox;

    public Sprite(Image image, Position position){
        this.entityIMG = image;
        this.position = position;
        this.hitBox = null;
    }

    public void setSpriteImage(Image img){
        if(img == null)
            return;
        entityIMG = img;
    }

    public void setPostion(double x, double y){
        position.setPostion(x, y);
    }

    public void moveSprite(double x, double y){
        this.position.updatePostion(x, y); 
    }
    
    public void setRotationDeg(double angleDeg) {position.setRotationDeg(angleDeg);}

    //getters
    public double[] getPosition(){ return position.getCenterPosition();}

    public double getRotationDeg() { return position.getRotationDeg();}

    public double[] getDimensions(){return position.getDimensions();}

    public Image getSpriteImage(){ return entityIMG; }
}
