package entity.Behavior;


public class TruckBehavior extends Behavior{
    public TruckBehavior(){

    }

    @Override
    public void playSound(){
        //TODO add truck noise sound USE OBSERVER
        System.out.println("Truck noise!");

    }
    @Override
    public void doAction(){
        //no special action for truck
    }
}
