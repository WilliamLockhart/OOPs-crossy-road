package entity.HitBox;

public class TruckHitBox extends HitBox {
    private final double spriteHeight;

    public TruckHitBox(double w, double spriteHeight, double angleDeg) {
        // hitbox is half as tall as the sprite
        super(w, spriteHeight / 1.8, angleDeg);
        this.spriteHeight = spriteHeight;
    }

    @Override
    public void setHitBoxPosition(double centerX, double centerY) {
        double w  = rectangle.getWidth();
        double hb = rectangle.getHeight();
        double topLeftX = centerX - w / 2.0;
        double topLeftY = centerY - hb / 2.0;
        double offsetY = spriteHeight * 0.5;

        rectangle.setX(topLeftX);
        rectangle.setY(topLeftY + offsetY);
    }
}
