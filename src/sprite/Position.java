package sprite;

public class Position {
    private double centerPositionX;
    private double centerPositionY;

    private double width;
    private double height;
    private double rotationDeg = 0.0; 

    public Position(double x, double y, double w, double h){
        setPostion(x, y);
        setSize(w, h);
    }
    
    //setters
    public void setSize(double width, double height){
        this.width = width;
        this.height = height;
    }

    public void setPostion(double x, double y){
        centerPositionX = x; centerPositionY = y;
    }

    public void setRotationDeg(double angleDeg) {this.rotationDeg = angleDeg;}
    
    public void updatePostion(double xChange, double ycChange){
        centerPositionX+= xChange;
        centerPositionY+= ycChange; }


    //getters
    public double getRotationDeg() {return rotationDeg;}

    public double[] getCenterPosition(){ return new double[] { centerPositionX, centerPositionY };}

    public double[] getDimensions(){
        return new double[] { width, height };
    }
}
