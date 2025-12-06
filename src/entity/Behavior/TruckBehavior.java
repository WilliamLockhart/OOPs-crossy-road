package entity.Behavior;

import observer.EventBus;
import observer.EventType;

public class TruckBehavior extends Behavior {

    public TruckBehavior() { }

    @Override
    public void playSound() {
        EventBus.getInstance().postMessage(EventType.TruckNoise, "Truck horn!");
    }
}
