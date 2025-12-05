package lanes;

import entity.Vehicle;

public class GrassLane extends Lane{
    private static final String fileName = "grass.png";

    public GrassLane(int index){
        super(index, fileName);
    }

    @Override 
    public Vehicle update(double dt){
        //grass lanes are safe zones nothing happens here
        return null;
    }
}
