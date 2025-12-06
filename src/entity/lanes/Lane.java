package entity.lanes;

import sprite.Sprite;
import sprite.SpriteFactory;
import window.WindowManager;

import java.util.ArrayList;
import java.util.List;
import entity.*;
import entity.HitBox.*;
import entity.vehicle.*;;

public abstract class Lane extends Entity {
   
    private static final double LANE_PERCENT_BIGGER_THAN_WINDOW = 1.6;//lane is at an angle so it must be bigger slightly 
    private static final double LANE_PERCENT_SCREEN_HEIGHT = .15;
    private static final double LANE_WIDTH  =  WindowManager.WINDOW_WIDTH*LANE_PERCENT_BIGGER_THAN_WINDOW;
    private static final double LANE_HEIGHT = WindowManager.WINDOW_HEIGHT*LANE_PERCENT_SCREEN_HEIGHT;
    private static final double LANE_ANGLE = 20.0;
    private static final double LANE_ANGLE_RAD = Math.toRadians(LANE_ANGLE);

    protected Sprite laneSprite;

    public Lane(int index, String fileName) {
        double x = generateXPos(index);
        double y = generateYPos(index);

        HitBox hitBox = new HitBox(LANE_WIDTH, LANE_HEIGHT, LANE_ANGLE);

        laneSprite = SpriteFactory.generateSprite(
            fileName,
            x, y,
            LANE_WIDTH, LANE_HEIGHT
        );

        this.sprite = laneSprite;
        hitBox.setHitBoxPosition(x, y);
        setHitBox(hitBox);
        laneSprite.setRotationDeg(LANE_ANGLE);
    }
    public abstract void update(double dt);

    //getters
    public List<Sprite> getLaneSprites() {
        List<Sprite> sprites = new ArrayList<>();
        sprites.add(laneSprite);
        return sprites;
    }

    public double[] getCenterPosition() { return laneSprite.getPosition();}

    //conditional checks
    public boolean hitAVehicle(HitBox playerHitBox){
        return false; //default
    }

    public boolean intersects(HitBox hb) {return getHitBox().checkCollision(hb);}


    // helper functions
    private double generateXPos(int index) {
        double baseCenterX = WindowManager.WINDOW_WIDTH * 0.2;
        double stepX = LANE_HEIGHT * Math.sin(LANE_ANGLE_RAD);
        return baseCenterX + index * stepX;
    }

    private double generateYPos(int index) {
        double baseCenterY = WindowManager.WINDOW_HEIGHT;
        double stepY = -LANE_HEIGHT * Math.cos(LANE_ANGLE_RAD);
        return baseCenterY + index * stepY;
    }
    
    // Sprite center & dimensions
    protected double[] getLanePositionForCarTravellingInDirection(Vehicle.Direction carDirection) {
        double[] pos = laneSprite.getPosition(); // [centerX, centerY]
        double cx = pos[0];
        double cy = pos[1];

        double halfW = LANE_WIDTH  / 2.0, halfH = LANE_HEIGHT / 2.0;

        double angle = Math.toRadians(laneSprite.getRotationDeg());
        double cos = Math.cos(angle), sin = Math.sin(angle);

        double tlx = cx + (-halfW * cos - -halfH * sin), tly = cy + (-halfW * sin + -halfH * cos);        // Top-left
        double blx = cx + (-halfW * cos - halfH * sin), bly = cy + (-halfW * sin + halfH * cos);        // Bottom-left
        double trx = cx + (halfW * cos - -halfH * sin), try_ = cy + (halfW * sin + -halfH * cos);        // Top-right
        double brx = cx + (halfW * cos - halfH * sin), bry = cy + (halfW * sin + halfH * cos); // Bottom-right

        double px, py;
        switch (carDirection) {
            case LEFT:
                px = (trx + brx) / 2.0;
                py = (try_ + bry) / 2.0;
                break;

            case RIGHT:
                px = (tlx + blx) / 2.0;
                py = (tly + bly) / 2.0;
                break;

            default:
                px = cx;
                py = cy;
                break;
        }

        return new double[]{px, py};
    }

}
