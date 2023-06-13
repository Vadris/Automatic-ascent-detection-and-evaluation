package myMath;

import java.util.ArrayList;

public class ElevationProfile {
    private ArrayList<DataPoint2D> profile = new ArrayList<>();

    public ElevationProfile(ArrayList<DataPoint2D> profile){
        this.profile = profile;
    }

    public void smooth(int windowSize){
        for(int i = 0; i < profile.size() - windowSize; i++){
            double meanElevation = 0;
            for(int j = 0; j < windowSize; j++){
                meanElevation += profile.get(i + j).getyValue();
            }
            meanElevation /= windowSize;
            for(int j = 0; j < windowSize; j++){
                if(profile.get(i +j).getyValue() < meanElevation){
                    double diff = meanElevation - profile.get(i + j).getyValue();
                    profile.get(i+j).setyValue(profile.get(i + j).getyValue() + diff*0.5);
                }
                if(profile.get(i +j).getyValue() > meanElevation){
                    double diff = profile.get(i + j).getyValue() - meanElevation;
                    profile.get(i+j).setyValue(profile.get(i + j).getyValue() - diff*0.5);
                }
            }
        }
    }

    public String toCSV(){
        StringBuilder csv = new StringBuilder();
        profile.forEach(dataPoint -> {
            csv.append(dataPoint.getxValue()).append(",").append(dataPoint.getyValue()).append(";\n");
        });
        return csv.toString();
    }
}
