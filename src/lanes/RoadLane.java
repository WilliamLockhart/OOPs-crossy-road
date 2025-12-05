package lanes;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import entity.VehicleFactory;
import sprite.HitBox;
import sprite.Sprite;
import entity.Vehicle.Direction;
import entity.Vehicle;


public class RoadLane extends Lane {
    private static final String fileName = "road.png";
    private final double vehicleSpeed;
    private final Direction roadDirection;
    private final Random random = new Random();
    List<Vehicle> activeVehicles;

    private static final double MIN_INTERVAL = 5.0;
    private static final double MAX_INTERVAL = 8.0;
    

    private double spawnTimer;

    public RoadLane(int index) {
        super(index, fileName);
        activeVehicles = new ArrayList<>();
        this.vehicleSpeed = 60.0;

        this.roadDirection = (random.nextBoolean())
                ? Direction.LEFT
                : Direction.RIGHT;
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
            HitBox vehicleHitBox = v.getSprite().getHitBox();
            if (playerHitBox.checkCollision(vehicleHitBox)) {
                return true;
            }
        }
        return false;
    }


    //helper functions

    private double randomInterval() {
        // random in range [MIN_INTERVAL, MAX_INTERVAL]
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
        double[] vPos = vehicle.getSprite().getEntityPosition();
        double vx = vPos[0];
        double vy = vPos[1];

        // Exit edge is opposite of travel direction
        Direction exitDir = (roadDirection == Direction.LEFT)
                ? Direction.RIGHT
                : Direction.LEFT;
        double[] exitPos = getLanePositionForCarTravellingInDirection(exitDir);
        double ex = exitPos[0];
        double ey = exitPos[1];

        // Vector from exit point to vehicle
        double dx = vx - ex;
        double dy = vy - ey;

        // Lane direction unit vector (RIGHT along the lane)
        double angle = Math.toRadians(laneSprite.getRotationDeg());
        double dirX = Math.cos(angle);
        double dirY = Math.sin(angle);

        // Signed distance along lane axis
        double along = dx * dirX + dy * dirY;

        double margin = vehicle.getSprite().getDimensions()[0] / 2.0;

        if (roadDirection == Direction.RIGHT) {
            // moving in +lane direction; off once we are margin past the exit
            return along > margin;
        } else {
            // moving in -lane direction; off once we are margin before the exit
            return along < -margin;
        }
    }

}
