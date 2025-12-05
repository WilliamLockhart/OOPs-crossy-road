package sprite.HitBox;

public class CarHitBox extends HitBox {
    private final double spriteHeight;

    public CarHitBox(double w, double spriteHeight, double angleDeg) {
        // hitbox is half as tall as the sprite
        super(w, spriteHeight, angleDeg);
        this.spriteHeight = spriteHeight;
    }

    @Override
    public void setHitBoxPosition(double centerX, double centerY) {
        double w  = rectangle.getWidth();
        double hb = rectangle.getHeight();
        double topLeftX = centerX - w / 2.0;
        double topLeftY = centerY - hb / 2.0;
        double offsetY = spriteHeight *.1;

        rectangle.setX(topLeftX);
        rectangle.setY(topLeftY + offsetY);
    }
}
