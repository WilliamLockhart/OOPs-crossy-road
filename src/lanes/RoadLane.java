package lanes;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import sprite.Sprite;
import entity.HitBox.*;
import entity.vehicle.*;
import entity.vehicle.Vehicle.Direction;

public class RoadLane extends Lane {
    private static final String fileName = "road.png";
    private static final double MIN_INTERVAL = 5.0;
    private static final double MAX_INTERVAL = 8.0;

    private final double vehicleSpeed;
    private final Direction roadDirection;
    private final Random random = new Random();
    
    List<Vehicle> activeVehicles;
    private double spawnTimer;

    public RoadLane(int index) {
        super(index, fileName);
        activeVehicles = new ArrayList<>();
        this.vehicleSpeed = 60.0;

        if (random.nextBoolean()) 
            this.roadDirection = Direction.LEFT;
        else 
            this.roadDirection = Direction.RIGHT;
        this.spawnTimer = randomInterval();
    }

    @Override
    public void update(double dt) {
        spawnTimer -= dt;

        if (spawnTimer <= 0.0) {
            spawnTimer = randomInterval();
            addVehicle(); }

        activeVehicles.removeIf(this::checkVehicleOffRoad);
        
        for(Vehicle v : activeVehicles)
            v.update(dt);
    }

    @Override
    public List<Sprite> getLaneSprites() {
        List<Sprite> sprites = new ArrayList<>();
        sprites.add(laneSprite);
        
        for(Vehicle v : activeVehicles)
            sprites.add(v.getSprite());
        return sprites;
    }

    @Override
    public boolean hitAVehicle(HitBox playerHitBox) {
        for (Vehicle v : activeVehicles) {
            HitBox vehicleHitBox = v.getHitBox();
            if (playerHitBox.checkCollision(vehicleHitBox))
                return true;
        }
        return false;
    }


    //helper functions

    private double randomInterval() {
        return MIN_INTERVAL + random.nextDouble() * (MAX_INTERVAL - MIN_INTERVAL);
    }

    private void addVehicle() {
        double[] laneSpawn = getLanePositionForCarTravellingInDirection(roadDirection);
        boolean spawnTruck = (random.nextInt(4) == 0);
        Vehicle vehicle;

        if (spawnTruck) { //god i hate the truck, its taller than car so the centerY is too high for the road and looks off below is to make it shift lower adjust values 
            vehicle = VehicleFactory.generateTruck(roadDirection);
            double[] dims = vehicle.getSprite().getDimensions();
            double spriteHeight = dims[1];
            double adjustedY = laneSpawn[1] - (.35 * spriteHeight / 2.0);
            vehicle.getSprite().setPostion(laneSpawn[0], adjustedY);
        } else {
            vehicle = VehicleFactory.generateCar(roadDirection);
            vehicle.getSprite().setPostion(laneSpawn[0], laneSpawn[1]);
        }

        vehicle.setSpeed(vehicleSpeed);
        activeVehicles.add(vehicle);
    }

    private boolean checkVehicleOffRoad(Vehicle vehicle) {
        double[] vehiclePos = vehicle.getSprite().getPosition();
        double[] exitPos = getExitPosition();
        double[] laneDir = getLaneDirectionUnitVector();

        double along = signedDistanceAlongLane(vehiclePos, exitPos, laneDir);
        double margin = vehicle.getSprite().getDimensions()[0] / 2.0;
        return isBeyondExit(along, margin);
    }

    private double[] getExitPosition() {
        Direction exitDir;
        if (roadDirection == Direction.LEFT) 
            exitDir = Direction.RIGHT;
        else 
            exitDir = Direction.LEFT;
        return getLanePositionForCarTravellingInDirection(exitDir);
    }

    private double[] getLaneDirectionUnitVector() {
        double angleRad = Math.toRadians(laneSprite.getRotationDeg());
        double dirX = Math.cos(angleRad);
        double dirY = Math.sin(angleRad);
        return new double[]{dirX, dirY};
    }

    private double signedDistanceAlongLane(double[] vehiclePos,double[] exitPos,double[] laneDir) {
        double vx = vehiclePos[0];
        double vy = vehiclePos[1];
        double ex = exitPos[0];
        double ey = exitPos[1];

        double dx = vx - ex;
        double dy = vy - ey;

        double dirX = laneDir[0];
        double dirY = laneDir[1];

        return dx * dirX + dy * dirY;
    }

    private boolean isBeyondExit(double along, double margin) {
        if (roadDirection == Direction.RIGHT)
            return along > margin;
        else 
            return along < -margin;
        
    }

}
