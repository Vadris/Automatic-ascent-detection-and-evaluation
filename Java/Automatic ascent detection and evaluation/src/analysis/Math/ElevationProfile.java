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
                double weight = Math.pow(steepness, -distance);
                
                if (profile.get(i + j).getY() < meanElevation) {
                    profile.get(i + j).setY(profile.get(i + j).getY() + distance * weight);
                } else if (profile.get(i + j).getY() > meanElevation) {
                    profile.get(i + j).setY(profile.get(i + j).getY() - distance * weight);
                }
            }
        }
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
    
    public void removeUnnecessaryDataPoints(){
        for(int i = 0; i < profile.size() - 2; i++){
            DataPoint currentPoint = profile.get(i);
            //Removes duplicate data points
            while(currentPoint.isEqual(profile.get(i + 1))){ 
                profile.remove(i + 1);
                i= 0;
            };
            //Removes 
            while(currentPoint.getY() == profile.get(i + 1).getY() && currentPoint.getY() == profile.get(i + 2).getY()){ 
                profile.remove(i + 1);
                i = 0;
            }
            //Combines datapoints with the same x values but diffrence y values by avraging the y values of the points
            if(currentPoint.getX() == profile.get(i + 1).getX()){
                double yValueAevrage = currentPoint.getY();
                int numberOfAvragingPoints = 1;
                while(currentPoint.getX() == profile.get(i + 1).getX()){
                    yValueAevrage += profile.get(i + 1).getY();
                    profile.remove(i + 1);
                    numberOfAvragingPoints++;
                }
                yValueAevrage /= numberOfAvragingPoints;
                profile.get(i).setY(yValueAevrage);
                i = 0;
            }
        }
        
    }
    
    public void spacePointsEvenly(double spacing){
        ArrayList<DataPoint> evenProfile = new ArrayList<>();;
        int i = 0;
        for(double currentXValue = profile.get(0).getX(); currentXValue < profile.get(profile.size() - 1).getX(); currentXValue += spacing){
            while(currentXValue >= profile.get(i).getX()) i++;
            double m = (profile.get(i).getY() - profile.get(i - 1).getY())/(profile.get(i).getX() - profile.get(i - 1).getX());
            double b = profile.get(i - 1).getY() - m * profile.get(i - 1).getX();
        
            evenProfile.add(new DataPoint(currentXValue, m * currentXValue + b));
        }    
        profile = evenProfile;
    }

    public SlopeData calculateSlopeData(){
        SlopeData slopeData = new SlopeData();
        for(int i = 1; i < profile.size(); i++){
            double currentSlope = Util.round((profile.get(i).getY() - profile.get(i - 1).getY())  /
            (profile.get(i).getX() - profile.get(i - 1).getX()), 2);
            
            double nextSlope = Util.round((profile.get(i + 1).getY() - profile.get(i).getY()) /
            (profile.get(i + 1).getX() - profile.get(i).getX()), 2);
            
            double slopeLength = profile.get(i + 1).getX() - profile.get(i ).getX();
            while(Util.isApproxEqual(currentSlope, nextSlope, 2)){
                slopeLength += profile.get(i + 1).getX() - profile.get(i).getX();
                i++;
            }
            slopeData.addSlopeSegment(new SlopeSegment(currentSlope, slopeLength));
        }
        return slopeData;
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
