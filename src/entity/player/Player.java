package entity.player;

import entity.player.OrientationAnimator.PlayerOrientation;
import javafx.scene.input.KeyCode;
import sprite.*;
import entity.Entity;
import entity.HitBox.PlayerHitBox;

public class Player extends Entity{
    private final OrientationAnimator orientationAnimator;
    private PlayerOrientation orientation;
    private final HopAnimator hopAnimator;

    public Player(Sprite sprite) {
        this.sprite = sprite;

        //create player hitbox
        double[] dims = sprite.getDimensions();
        PlayerHitBox hitBox = new PlayerHitBox(dims[0], dims[1], 0);
        setHitBox(hitBox);
        //animation stuff
        this.orientationAnimator = new OrientationAnimator();
        this.orientation = PlayerOrientation.FORWARD_RIGHT;
        this.sprite.setSpriteImage(orientationAnimator.getImage(orientation));
        this.hopAnimator = new HopAnimator(sprite);
    }

    private void setOrientation(PlayerOrientation orientation) {
        if (this.orientation == orientation)
            return;
        this.orientation = orientation;
        sprite.setSpriteImage(orientationAnimator.getImage(orientation));
    }

    public void update(double dt){} //player needs input to update, so this wont do anything

    public void update(double dt, Input input) {
        if (!hopAnimator.isHopping()) 
            handleIdle(input);
         else {
            hopAnimator.update(dt);
            double[] pos = sprite.getPosition();
            setEntityPostion(pos[0], pos[1]);    
        }
    }

    private void handleIdle(Input input) {
        DirectionInput dirInput = readDirectionFromInput(input);
        if(!dirInput.hasDirection())
            return;
    
        setOrientation(dirInput.orientation());
        hopAnimator.startHop(dirInput.directionX(), dirInput.directionY());
    }

    private DirectionInput readDirectionFromInput(Input input) {
        double directionX = 0; double directionY = 0; //default values for nothing happened
        PlayerOrientation newOrientation = this.orientation;

        if (input.isKeyDown(KeyCode.W)) {
            directionX = 1; directionY = -1;
            newOrientation = PlayerOrientation.FORWARD_RIGHT;
        } else if (input.isKeyDown(KeyCode.S)) {
            directionX = -1; directionY = 1;
            newOrientation = PlayerOrientation.BACK_LEFT;
        } else if (input.isKeyDown(KeyCode.A)) {
            directionX = -1; directionY = -1;
            newOrientation = PlayerOrientation.FORWARD_LEFT;
        } else if (input.isKeyDown(KeyCode.D)) {
            directionX = 1; directionY = 1;
            newOrientation = PlayerOrientation.BACK_RIGHT; }

        return new DirectionInput(directionX, directionY, newOrientation);
    }

    // helper class for direction and orientation
    private static class DirectionInput {
        private final double directionX;
        private final double directionY;
        private final PlayerOrientation orientation;

        DirectionInput(double directionX, double directionY, PlayerOrientation orientation) {
            this.directionX = directionX;
            this.directionY = directionY;
            this.orientation = orientation;}

        boolean hasDirection() {
            return directionX != 0 || directionY != 0; }

        double directionX() { return directionX; }
        double directionY() { return directionY; }
        PlayerOrientation orientation() { return orientation; }
    }
}
