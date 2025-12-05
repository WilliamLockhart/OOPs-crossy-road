package gameManager;

import lanes.*;
import sprite.*;

import java.util.ArrayList;
import java.util.List;
import sprite.HitBox.*;
import entity.player.*;

public class GameManager {
    private Player player;
    private boolean gameOver;
    private int tempCounter = 0;
    private List<Lane> lanes;

    public GameManager(Player player) {
        this.gameOver = false;
        this.player = player;
        this.lanes = new ArrayList<>();
        generateLanes(12);
    }

    public void update(double dt, Input input) {
        updateLanes(dt);
        player.update(dt, input);

        if (playerHitAVehicle()) {
            tempCounter++;
            System.out.printf("Bang! Count = %d%n", tempCounter);
            gameOver= true;
        }
    }

    boolean playerHitAVehicle() {
        HitBox playerHitBox = player.getSprite().getHitBox();
        for(Lane lane : lanes)
            if(lane.intersects(playerHitBox))
                if(lane.hitAVehicle(playerHitBox))
                    return true;
        return false;
    }

    void updateLanes(double dt) {
        for (Lane lane : lanes) 
            lane.update(dt);
    }

    public boolean gameOver() { return gameOver; }

    public List<Sprite> getAllSprites() {
        List<Sprite> sprites = new ArrayList<>();

        for (int i = lanes.size() - 1; i >= 0; i--)
            sprites.addAll(lanes.get(i).getLaneSprites());
    
        sprites.add(player.getSprite());
        return sprites;
    }

    void generateLanes(int numberOfLanes) {
        for (int i = 0; i < numberOfLanes; i++) {
            if (i % 2 == 0) {
                lanes.add(new RoadLane(i));
            } else {
                lanes.add(new GrassLane(i));
            }
        }
    }
}
