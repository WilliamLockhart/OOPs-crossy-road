package gameManager;

import lanes.*;
import entity.*;
import entity.Vehicle.*;
import player.*;
import sprite.*;
import window.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Vehicle> activeVehicles;
    private Player player;
    private boolean gameOver;
    int tempCounter = 0;
    private List<Lane> lanes;

    public GameManager(Player player){
        gameOver = false;
        this.player = player;
        activeVehicles = new ArrayList<>();
        lanes = new ArrayList<>();
        generateLanes(12);

    }

    public void update(double dt, Input input){
        updateLanes(dt);
        player.update(dt, input);
        
        updateVehicles(dt);


        if(playerHitAVehicle()){
            tempCounter++;
            System.out.printf("Bang! Count = %d%n", tempCounter);}
    }

    boolean playerHitAVehicle(){
        HitBox playeHitBox = player.getSprite().getHitBox();
        for (Vehicle vehicle : activeVehicles){
            HitBox vehicleHitBox = vehicle.getSprite().getHitBox();    
            if(playeHitBox.checkCollision(vehicleHitBox))
                return true;    
        } 
        return false;
    }

    void updateLanes(double dt){
        for(Lane lane : lanes){
            Vehicle v = lane.update(dt);
            if(v != null)
                activeVehicles.add(v);
        }

    }

    void updateVehicles(double dt){
        double[] playerPosition = player.getSprite().getEntityPosition();
        double playerX = playerPosition[0];
        double playerY = playerPosition[1];

        var it = activeVehicles.iterator();
        while (it.hasNext()) {
            Vehicle vehicle = it.next();

            double[] vehiclePosition = vehicle.getSprite().getEntityPosition();
            double vehicleX = vehiclePosition[0];
            double vehicleY = vehiclePosition[1];

            if (shouldRemoveVehicle(vehicleX, vehicleY, playerX, playerY)) {
                it.remove();
            } else {
                vehicle.update(dt);
            }
        }
    }

    private boolean shouldRemoveVehicle(double x1, double y1, double x2, double y2) {
        double distance = Math.hypot(x2 - x1, y2 - y1);
        return distance > WindowManager.WINDOW_WIDTH * 2;
    }

    public boolean gameOver(){return gameOver;}

    public List<Sprite> getAllSprites(){
        List<Sprite> sprites = new ArrayList<>();

        //ORDER MATTERS, first things added will be rendered first tf, will be on bottom!!
        //TODO return list based on INDEX of ROAD inverted i.e further roads rendered first so earlier items will render on top of further ones!

        for(Lane lane : lanes)
            sprites.add(lane.getSprite());

        for(Vehicle vehicle : activeVehicles){
            sprites.add(vehicle.getSprite()); }

        sprites.add(player.getSprite());
        return sprites;
    }



    void generateLanes(int numberOfLanes){
        for(int i =0; i < numberOfLanes; i++){
            if(i%2 == 0){
                lanes.add(new RoadLane(i));
            }
            else{
                lanes.add(new GrassLane(i));
            }
        }
    }
}
