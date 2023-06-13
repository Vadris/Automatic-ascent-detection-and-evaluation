package myMath;

import java.util.ArrayList;

public class GraphPoint {
    private double xValue;
    private double yValue;

    public GraphPoint(double xValue, double yValue){
        this.xValue = xValue;
        this.yValue = yValue;
    }
    
    public double getxValue() {
        return xValue;
    }
    public double getyValue() {
        return yValue;
    }
    @Override
    public String toString(){
        return xValue + "," + yValue;
    }
    public static String convertPointListToCSV(ArrayList<GraphPoint> dataPoints){
        StringBuilder csv = new StringBuilder();
        for(int i = 0; i < dataPoints.size(); i++){
            csv.append(dataPoints.get(i).getxValue()).append(",").append(dataPoints.get(i).getyValue()).append(";\n");
        }
        return csv.toString();
    }
}
