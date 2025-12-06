package entity.Behavior;

import observer.EventBus;
import observer.EventType;

public class CarBehavior extends Behavior {

    public CarBehavior() { }

    @Override
    public void playSound() {
        EventBus.getInstance().postMessage(EventType.CarNoise, "Car honk!");
    }

}
