package entity.vehicle;
import sprite.Sprite;
import entity.Behavior.*;
import entity.Entity;
import java.util.Random;

public class Vehicle extends Entity{
    public enum Direction{
        LEFT,
        RIGHT
    };
    private final Random random = new Random();
    private double speed;      
    private Direction direction;
    static final private double ANGLE_RAD = Math.toRadians(20.0);
    public Vehicle(Sprite sprite, Behavior behavior) {this.sprite = sprite; this.behavior = behavior;}
    public void setSpeed(double speed){this.speed = speed;}
    public void setDirection(Direction direction){this.direction = direction;}
    
    public void update(double dt) {
        if (direction == null) return;

        double distance = speed * dt;
        double dx = distance * Math.cos(ANGLE_RAD);
        double dy = distance * Math.sin(ANGLE_RAD); 

        switch (direction) {
            case RIGHT:
                moveEntity(dx, dy);
                break;

            case LEFT:
                moveEntity(-dx, -dy);
                break;
        }
    

        int honkChance = 2000;               // 1 in honkChance chance
    boolean shouldHonk = (random.nextInt(honkChance) == 0);
    if (shouldHonk)
        playSound();
}
    public double getAngleRad(){return ANGLE_RAD;}

}
