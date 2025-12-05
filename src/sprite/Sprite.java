package sprite;
import javafx.scene.image.Image;

public class Sprite {
    private Image entityIMG;
    private Position position;

    public Sprite(Image image, Position position){
        this.entityIMG = image;
        this.position = position;
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
