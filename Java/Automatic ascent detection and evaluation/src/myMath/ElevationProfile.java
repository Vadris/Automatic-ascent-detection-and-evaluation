package myMath;

import java.util.ArrayList;

public class ElevationProfile {
    private ArrayList<DataPoint2D> profile = new ArrayList<>();

    /**
     * Constructs an elevation profile with the specified data points.
     *
     * @param profile The elevation profile data points.
     */
    public ElevationProfile(ArrayList<DataPoint2D> profile) {
        this.profile = profile;
    }

    /**
     * Smoothes the elevation profile using a moving window averaging approach.
     * The window size determines the number of data points used for the averaging.
     *
     * @param windowSize The size of the moving window.
     */
    public void smooth(int windowSize) {
        for (int i = 0; i < profile.size() - windowSize; i++) {
            double meanElevation = 0;
            for (int j = 0; j < windowSize; j++) {
                meanElevation += profile.get(i + j).getyValue();
            }
            meanElevation /= windowSize;
            for (int j = 0; j < windowSize; j++) {
                if (profile.get(i + j).getyValue() < meanElevation) {
                    double diff = meanElevation - profile.get(i + j).getyValue();
                    profile.get(i + j).setyValue(profile.get(i + j).getyValue() + diff * 0.5);
                }
                if (profile.get(i + j).getyValue() > meanElevation) {
                    double diff = profile.get(i + j).getyValue() - meanElevation;
                    profile.get(i + j).setyValue(profile.get(i + j).getyValue() - diff * 0.5);
                }
            }
        }
    }
    public void filterVertical(double numberOfLines){
        double min = profile.get(0).getxValue();
        double max = profile.get(profile.size() - 1).getxValue();
        double range = max - min;
        ArrayList<DataPoint2D> intersects = new ArrayList<>();
        
        int j = 0;
        for(int i = 0; i < numberOfLines; i++){
            double currentXValue = min + i * (range/numberOfLines);
            while(profile.get(j).getxValue() < currentXValue){
                j++;
            }
            intersects.add(profile.get(j));
        }
        profile = intersects;
    }

    public ArrayList<DataPoint2D> findExtremalPoints(){
        ArrayList<DataPoint2D> extremalPoints = new ArrayList<>();
        boolean ascending = true;
        boolean oldAscending = true;
        double highDiffrence = 0;
        for(int i = 0; i < profile.size() - 1; i++){
            if(ascending) oldAscending = true;
            else oldAscending = false;
            highDiffrence = profile.get(i).getyValue() - profile.get(i + 1).getyValue();
            if(highDiffrence >= 1) ascending = true;
            else ascending = false;
            if(ascending && !oldAscending){
                extremalPoints.add(profile.get(i));
                System.out.println(profile.get(i));
            }
            if(!ascending && oldAscending) extremalPoints.add(profile.get(i));
        }
        return extremalPoints;
    }

    public int getDataSetSize(){
        return profile.size();
    }

    public double getDistanceRange(){
        double maxDistance = 0;
        double minDistance = profile.get(1).getxValue() - profile.get(0).getxValue();
        for(int i = 0; i < profile.size() - 1; i++){
            double curretDistance = profile.get(i + 1).getxValue() - profile.get(i).getxValue();
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
            csv.append(dataPoint.getxValue()).append(",").append(dataPoint.getyValue()).append(";\n");
        });
        return csv.toString();
    }
}
