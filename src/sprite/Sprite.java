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

    //setters
    public void setHitBox(HitBox hitBox){
        this.hitBox = hitBox;
        updateHitbox();
    }

    public void setSpriteImage(Image img){
        if(img == null)
            return;
        entityIMG = img;
    }

    public void setPostion(double x, double y){
        position.setPostion(x, y);
        updateHitbox();
    }

    public void moveSprite(double x, double y){
        this.position.updatePostion(x, y); 
        updateHitbox();
    }
    
    public void setRotationDeg(double angleDeg) {position.setRotationDeg(angleDeg);}

    //getters
    public double[] getEntityPosition(){ return position.getCenterPosition();}

    public double getRotationDeg() { return position.getRotationDeg();}

    public double[] getDimensions(){return position.getDimensions();}

    public Image getSpriteImage(){ return entityIMG; }

    public HitBox getHitBox(){return hitBox;}

    //helper functions
    private void updateHitbox(){
        if(hitBox == null)
            return;

        double[] pos = position.getCenterPosition();
        hitBox.setHitBoxPosition(pos[0], pos[1]);
    }
}
