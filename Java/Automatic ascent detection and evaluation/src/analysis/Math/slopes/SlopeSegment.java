package analysis.Math.slopes;

public class SlopeSegment {
    double slope;  //
    double length; //Length of the slope in m
    
    public SlopeSegment(double slope, double length){
        this.slope = slope;
        this.length = length;
    }
    
    public double getSlope() {
        return slope;
    }
    public double getLength() {
        return length;
    }
}
