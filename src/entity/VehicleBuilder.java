package entity;

import entity.Behavior.Behavior;
import sprite.Sprite;

public class VehicleBuilder {

    private Sprite sprite;
    private Behavior behavior;
    private Vehicle.Direction direction;
    private double speed;

    public VehicleBuilder sprite(Sprite sprite) {
        this.sprite = sprite;
        return this;
    }

    public VehicleBuilder behavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public VehicleBuilder direction(Vehicle.Direction direction) {
        this.direction = direction;
        return this;
    }

    public VehicleBuilder speed(double speed) {
        this.speed = speed;
        return this;
    }

    public Vehicle build() {
        Vehicle v = new Vehicle(sprite, behavior);

        if (direction != null)
            v.setDirection(direction);
        
        v.setSpeed(speed);

        return v;
    }
}
