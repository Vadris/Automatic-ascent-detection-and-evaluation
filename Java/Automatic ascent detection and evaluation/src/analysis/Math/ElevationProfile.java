package analysis.Math;

import java.util.ArrayList;
import analysis.Math.Util;

public class ElevationProfile {
    private ArrayList<DataPoint> profile = new ArrayList<>();
    
    /**
    * Constructs an elevation profile with the specified data points.
    *
    * @param profile The elevation profile data points.
    */
    public ElevationProfile(ArrayList<DataPoint> profile) {
        this.profile = profile;
    }
    
    /**
    * Smoothes the elevation profile using a moving window averaging approach.
    * The window size determines the number of data points used for the averaging.
    *
    * @param windowSize The size of the moving window.
    */
    public void smooth(int windowSize, double steepness) {
        for (int i = 0; i < profile.size() - windowSize; i++) {
            // Calculates the mean of the elevation values inside the window
            double meanElevation = 0;
            for (int j = 0; j < windowSize; j++) {
                meanElevation += profile.get(i + j).getY();
            }
            meanElevation /= windowSize;
            
            for (int j = 0; j < windowSize; j++) {
                double distance = Math.abs(profile.get(i + j).getY() - meanElevation);
                double weight = Math.pow(steepness, 0.02 * distance);
                
                if (profile.get(i + j).getY() < meanElevation) {
                    profile.get(i + j).setY(profile.get(i + j).getY() + distance * weight);
                } else if (profile.get(i + j).getY() > meanElevation) {
                    profile.get(i + j).setY(profile.get(i + j).getY() - distance * weight);
                }
            }
        }
    }
    
    public void filterVertical(double numberOfLines){
        double min = profile.get(0).getX();
        double max = profile.get(profile.size() - 1).getX();
        double range = max - min;
        ArrayList<DataPoint> intersects = new ArrayList<>();
        
        int j = 0;
        for(int i = 0; i < numberOfLines; i++){
            double currentXValue = min + i * (range/numberOfLines);
            while(profile.get(j).getX() < currentXValue){
                j++;
            }
            intersects.add(profile.get(j));
        }
        profile = intersects;
    }
    
    public ArrayList<DataPoint> findExtremalPoints(){
        ArrayList<DataPoint> extremalPoints = new ArrayList<>();
        boolean ascending = true;
        boolean oldAscending = true;
        double highDiffrence = 0;
        for(int i = 0; i < profile.size() - 1; i++){
            if(ascending) oldAscending = true;
            else oldAscending = false;
            highDiffrence = profile.get(i).getY() - profile.get(i + 1).getY();
            if(highDiffrence >= 1) ascending = true;
            else ascending = false;
            if(ascending && !oldAscending){
                extremalPoints.add(profile.get(i));
            }
            if(!ascending && oldAscending) extremalPoints.add(profile.get(i));
        }
        return extremalPoints;
    }
    
    public void removeDuplicatePoints(){
        for(int i = 0; i < profile.size() - 1; i++){
            DataPoint currentPoint = profile.get(i);
            while(currentPoint.isEqual(profile.get(i + 1))){
                profile.remove(i + 1);
            }
        }
    }
    
    public void spacePointsEvenly(double spacing){
        ArrayList<DataPoint> evenProfile = new ArrayList<>();
        //evenProfile.add(profile.get(0));
        int i = 0;
        for(double currentXValue = profile.get(0).getX(); currentXValue < profile.get(profile.size() - 1).getX(); currentXValue += spacing){
            while(currentXValue >= profile.get(i).getX()) i++;
            evenProfile.add(new DataPoint(currentXValue, evalueateLinearInterpolation(currentXValue, profile.get(i - 1), profile.get(i))));
        }    
        profile = evenProfile;
    }

    private double evalueateLinearInterpolation(double xValue, DataPoint point1, DataPoint point2){
        double m = (point2.getY() - point1.getY())/(point2.getX() - point1.getX());
        double b = point1.getY() - m * point1.getX();
        return m*xValue + b;
    }
    
    public void checkPointSpacing(){
        for(int i = 0; i < profile.size() - 1; i++){
            System.out.println(profile.get(i + 1).getX() - profile.get(i).getX());
        }
    }
    
    
    public int getDataSetSize(){
        return profile.size();
    }  
    /** 
    * Converts the elevation profile to a CSV string.
    *
    * @return The CSV representation of the elevation profile.
    */
    public String toCSV() {
        StringBuilder csv = new StringBuilder();
        profile.forEach(dataPoint -> {
            csv.append(dataPoint.getX()).append(",").append(dataPoint.getY()).append(";\n");
        });
        return csv.toString();
    }
}
