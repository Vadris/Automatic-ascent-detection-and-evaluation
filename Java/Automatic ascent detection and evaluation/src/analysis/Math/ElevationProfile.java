package analysis.Math;

import java.util.ArrayList;

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
                    double difference = meanElevation - profile.get(i + j).getY();
                    profile.get(i + j).setY(profile.get(i + j).getY() + distance * weight);
                } else if (profile.get(i + j).getY() > meanElevation) {
                    double difference = profile.get(i + j).getY() - meanElevation;
                    profile.get(i + j).setY(profile.get(i + j).getY() - distance * weight);
                }
            }
        }
    }

    public ArrayList<Double> calculateSlope(){
        ArrayList<Double> slopeValues = new ArrayList<>();
        for(int i = 0; i < profile.size() - 1; i++){
            double slope = 
            round((profile.get(i + 1).getY() - profile.get(i).getY())/(profile.get(i + 1).getX() - profile.get(i).getX()), 2);            
            if(slopeValues.isEmpty()) slopeValues.add(slope);
            else if(slopeValues.get(slopeValues.size() - 1) != slope) slopeValues.add(slope);
        }
        return slopeValues;
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
    
    public int getDataSetSize(){
        return profile.size();
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public double getDistanceRange(){
        double maxDistance = 0;
        double minDistance = profile.get(1).getX() - profile.get(0).getX();
        for(int i = 0; i < profile.size() - 1; i++){
            double curretDistance = profile.get(i + 1).getX() - profile.get(i).getX();
            if(curretDistance > maxDistance) maxDistance = curretDistance;
            if(curretDistance < minDistance) minDistance = curretDistance;
        }
        //System.out.println(maxDistance);
        //System.out.println(minDistance);
        return maxDistance - minDistance;
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
