package lanes;

import sprite.Sprite;
import sprite.SpriteFactory;
import window.WindowManager;
import sprite.HitBox;

import java.util.ArrayList;
import java.util.List;

import entity.Vehicle;

public abstract class Lane {

    private static final double LANE_WIDTH  = 1800.0;
    private static final double LANE_HEIGHT = 100.0;
    private static final double LANE_ANGLE = 20.0;
    private static final double LANE_ANGLE_RAD = Math.toRadians(LANE_ANGLE);

    protected Sprite laneSprite;

    public Lane(int index, String fileName) {
        double x = generateXPos(index);
        double y = generateYPos(index);

        HitBox hitBox = new HitBox(LANE_WIDTH, LANE_HEIGHT, LANE_ANGLE);
        hitBox.setHitBoxPosition(x, y);

        laneSprite = SpriteFactory.generateSprite(
            "lane_" + index, fileName,
            x, y,
            LANE_WIDTH, LANE_HEIGHT
        );
        laneSprite.setHitBox(hitBox);
        laneSprite.setRotationDeg(LANE_ANGLE);
    }

    public abstract void update(double dt);

    public List<Sprite> getLaneSprites() {
        List<Sprite> sprites = new ArrayList<>();
        sprites.add(laneSprite);
        return sprites;
    }

    public double[] getCenterPosition() { return laneSprite.getEntityPosition();}

    public boolean hitAVehicle(HitBox playerHitBox){
        return false; //default
    }

    public boolean intersects(HitBox hb) {return laneSprite.getHitBox().checkCollision(hb);}


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

    protected double[] getLanePositionForCarTravellingInDirection(Vehicle.Direction carDirection) {
        // Sprite center & dimensions
        double[] pos = laneSprite.getEntityPosition(); // [centerX, centerY]
        double cx = pos[0];
        double cy = pos[1];

        double halfW = LANE_WIDTH  / 2.0;
        double halfH = LANE_HEIGHT / 2.0;

        double angle = Math.toRadians(laneSprite.getRotationDeg());
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        // Top-left
        double tlx = cx + (-halfW * cos - -halfH * sin);
        double tly = cy + (-halfW * sin + -halfH * cos);

        // Bottom-left
        double blx = cx + (-halfW * cos - halfH * sin);
        double bly = cy + (-halfW * sin + halfH * cos);

        // Top-right
        double trx = cx + (halfW * cos - -halfH * sin);
        double try_ = cy + (halfW * sin + -halfH * cos);

        // Bottom-right
        double brx = cx + (halfW * cos - halfH * sin);
        double bry = cy + (halfW * sin + halfH * cos);

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
