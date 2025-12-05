package lanes;

import java.util.Random;

import entity.VehicleFactory;
import entity.Vehicle.Direction;
import entity.Vehicle;

public class RoadLane extends Lane {
    private static final String fileName = "road.png";

    private final double vehicleSpeed;     // fixed simplified speed
    private final Direction roadDirection;
    private final Random random = new Random();

    // simplified spawn interval (seconds)
    private static final double MIN_INTERVAL = 5.0;   // slow traffic
    private static final double MAX_INTERVAL = 8.0;   // even slower traffic
    
    private double spawnTimer;

    public RoadLane(int index) {
        super(index, fileName);

        // fixed reasonable vehicle speed
        this.vehicleSpeed = 60.0;

        // random lane direction
        this.roadDirection = (random.nextBoolean())
                ? Direction.LEFT
                : Direction.RIGHT;

        // initial delay before first spawn
        this.spawnTimer = randomInterval();
    }

    @Override
    public Vehicle update(double dt) {
        spawnTimer -= dt;

        // time to spawn a new vehicle?
        if (spawnTimer <= 0.0) {
            spawnTimer = randomInterval();
            return addVehicle();
        }

        return null;
    }

    private double randomInterval() {
        // random in range [MIN_INTERVAL, MAX_INTERVAL]
        return MIN_INTERVAL + random.nextDouble() * (MAX_INTERVAL - MIN_INTERVAL);
    }

   
    private Vehicle addVehicle() {
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
        return vehicle;
    }

}
