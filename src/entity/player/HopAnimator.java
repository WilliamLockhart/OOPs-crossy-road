package entity.player;

import sprite.Sprite;

public class HopAnimator {

    private final Sprite sprite;

    private boolean isHopping = false;
    private double hopTimer = 0.0;

    private double startX, startY;
    private double targetX, targetY;

    private static final double HOP_DURATION = 0.15; // seconds
    private static final double HOP_DISTANCE = 50;   // pixels
    private static final double JUMP_HEIGHT = 16;    // pixels


    public HopAnimator(Sprite sprite) {this.sprite = sprite;}

    public boolean isHopping() { return isHopping;}

    public void startHop(double dirX, double dirY) {
        //calculates the end position for the sprite, and set timer to 0
        if (dirX == 0 && dirY == 0) return; //not WASD

        double[] pos = sprite.getEntityPosition();
        startX = pos[0]; startY = pos[1];

        targetX = startX + dirX * HOP_DISTANCE;
        targetY = startY + dirY * HOP_DISTANCE;

        hopTimer = 0.0;
        isHopping = true;
    }

    public void update(double dt) {
        if (!isHopping) return;

        hopTimer += dt;
        double timeInHop = Math.min(hopTimer / HOP_DURATION, 1.0); // to prevent going past end of hop

        // position change
        double xPosition = startX + (targetX - startX) * timeInHop;
        double baseY = startY + (targetY - startY) * timeInHop;
        double arcOffset = -JUMP_HEIGHT * Math.sin(Math.PI * timeInHop); //  follow sin to get the height of the bounce and add curve 
        sprite.setPostion(xPosition, baseY + arcOffset);

        if (timeInHop == 1.0) {
            sprite.setPostion(targetX, targetY);
            isHopping = false; 
        }
}

}
