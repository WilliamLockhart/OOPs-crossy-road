package entity.HitBox;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class HitBox {
    protected Rectangle rectangle;

    public HitBox(double w, double h, double angleDeg) {
        rectangle = new Rectangle(w, h);
        rectangle.setRotate(angleDeg);
    }

    public void setHitBoxPosition(double centerX, double centerY) {
        double w = rectangle.getWidth();
        double h = rectangle.getHeight();

        rectangle.setX(centerX - w / 2.0);
        rectangle.setY(centerY - h / 2.0);
    }

    public boolean checkCollision(HitBox other) {
        if (other == null) return false;
        Shape intersection = Shape.intersect(this.rectangle, other.rectangle);
        return !intersection.getBoundsInLocal().isEmpty();
    }

    public Rectangle getRectangle() {return rectangle;}
}
