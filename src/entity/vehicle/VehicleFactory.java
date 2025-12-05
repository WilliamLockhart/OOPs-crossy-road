package entity.vehicle;

import sprite.*;
import sprite.HitBox.*;
import entity.Behavior.*;

public class VehicleFactory {

    private static final String RIGHT_TRUCK_FILE = "Red_Truck.png";
    private static final String LEFT_TRUCK_FILE  = "Green_Truck.png";

    private static final String RIGHT_CAR_FILE = "Purple_car.png";
    private static final String LEFT_CAR_FILE  = "Orange_car.png";

    private static final double TRUCK_W = 200, TRUCK_H = 200;
    private static final double CAR_W   = 100, CAR_H   = 100;

    private static final double DEFAULT_X = -1000;
    private static final double DEFAULT_Y = -1000;


    public static Vehicle generateCar(Vehicle.Direction dir) {
        Vehicle car = createVehicle(
            chooseFilename(dir, LEFT_CAR_FILE, RIGHT_CAR_FILE),
            CAR_W, CAR_H,
            new CarBehavior(),
            dir
        );

        Sprite carSprite = car.getSprite();
        carSprite.setHitBox(createHitBox(CAR_W, CAR_H, car.getAngleRad(), false));
        return car;
    }

    public static Vehicle generateTruck(Vehicle.Direction dir) {
        Vehicle truck = createVehicle(
            chooseFilename(dir, LEFT_TRUCK_FILE, RIGHT_TRUCK_FILE),
            TRUCK_W, TRUCK_H,
            new TruckBehavior(),
            dir
        );

        Sprite truckSprite = truck.getSprite();
        truckSprite.setHitBox(createHitBox(TRUCK_W, TRUCK_H, truck.getAngleRad(), true));
        return truck;
    }

    // Helper functions
    private static Vehicle createVehicle(
            String filename,
            double w,
            double h,
            Behavior behavior,
            Vehicle.Direction dir
    ) {
        Sprite sprite = SpriteFactory.generateSprite(
            filename,
            DEFAULT_X, DEFAULT_Y,
            w, h
        );

        return new VehicleBuilder()
                .sprite(sprite)
                .behavior(behavior)
                .direction(dir)
                .build();
    }

    private static String chooseFilename(Vehicle.Direction dir, String left, String right) {
        return (dir == Vehicle.Direction.LEFT) ? left : right;
    }

    private static HitBox createHitBox(double w, double h, double angleRad, boolean truck) {
        double angleDeg = Math.toDegrees(angleRad);

        if (truck) {
            return new TruckHitBox(w, h / 2, angleDeg);
        } else {
            return new CarHitBox(w, h / 2, angleDeg);
        }
    }
}
