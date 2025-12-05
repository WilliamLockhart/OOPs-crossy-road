package lanes;

import sprite.Sprite;
import sprite.SpriteFactory;
import window.WindowManager;

public abstract class Lane {

    private static final double LANE_WIDTH  = 1800.0; // length along the road
    private static final double LANE_HEIGHT = 100.0;  // lane thickness (spacing)

    private static final double LANE_ANGLE = 20.0;
    private static final double LANE_ANGLE_RAD = Math.toRadians(LANE_ANGLE);

    protected Sprite sprite;

    public Lane(int index, String fileName) {
        double x = generateXPos(index);
        double y = generateYPos(index);

        sprite = SpriteFactory.generateSprite(
            "lane_" + index, fileName,
            x, y,
            LANE_WIDTH, LANE_HEIGHT
        );

        sprite.setRotationDeg(LANE_ANGLE);
    }

    public abstract void update(double dt);

    public Sprite getSprite() {
        return sprite;
    }

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

}
