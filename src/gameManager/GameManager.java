package gameManager;

import entity.*;
import entity.Vehicle.*;
import player.*;
import sprite.*;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Vehicle> activeVehicles;
    private Player player;
    private boolean gameOver;
    int tempCounter = 0;

    public GameManager(Player player){
        gameOver = false;
        this.player = player;
        activeVehicles = new ArrayList<>();
        Vehicle truck = (VehicleFactory.generateTruck(Direction.RIGHT));
        truck.getSprite().setPostion(0, 0);
        truck.setSpeed(10);
        activeVehicles.add(truck);
    }

    public void update(double dt, Input input){
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

    void updateVehicles(double dt){
        for(Vehicle vehicle : activeVehicles)
            vehicle.update(dt);
    }

    boolean gameOver(){return gameOver;}

    public List<Sprite> getAllSprites(){
        List<Sprite> sprites = new ArrayList<>();

        //ORDER MATTERS, first things added will be rendered first tf, will be on bottom!!
        //TODO return list based on INDEX of ROAD inverted i.e further roads rendered first so earlier items will render on top of further ones!
        for(Vehicle vehicle : activeVehicles){
            sprites.add(vehicle.getSprite()); }

        sprites.add(player.getSprite());
        return sprites;
    }
}
