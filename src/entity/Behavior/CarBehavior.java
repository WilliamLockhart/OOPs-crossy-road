package entity.Behavior;

public class CarBehavior extends Behavior{
    public CarBehavior(){

    }

    @Override
    public void playSound(){
        //TODO add car noise sound USE OBSERVER
        System.out.println("Car noise!");
    }

    @Override
    public void doAction(){
        //no special action for car
    }
}
