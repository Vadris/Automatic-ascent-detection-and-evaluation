package analysis.Math.slopes;

import java.util.ArrayList;

public class SlopeData {
    private ArrayList<SlopeSegment> slopeSegments = new ArrayList<>();

    public void addSlopeSegment(SlopeSegment slopeSegment){
        slopeSegments.add(slopeSegment);
    }

    public SlopeSegment getSlopeSegment(int index){
        return slopeSegments.get(index);
    }
    public int getNumberOfSlopeSegments(){
        return slopeSegments.size();
    }

    public void printSlopeType(){
        for(int i = 0; i < slopeSegments.size(); i++){
            if(slopeSegments.get(i).getSlope() > 0) System.out.println("Rising for  " + slopeSegments.get(i).getLength()/1000 + "km with a slope of " + slopeSegments.get(i).getSlope() + "m/km.");
            else if(slopeSegments.get(i).getSlope() < 0) System.out.println("Falling for " + slopeSegments.get(i).getLength()/1000 + "km with a slope of " + slopeSegments.get(i).getSlope() + "m/km.");
            else System.out.println("Flat for    " + slopeSegments.get(i).getLength()/1000 + "km with a slope of " + slopeSegments.get(i).getSlope() + "m/km.");
        }
    }

    public String toCSV(){
        StringBuilder csv = new StringBuilder();
        slopeSegments.forEach(slopeSegment -> {
            csv.append(slopeSegment.getSlope()).append(",").append(slopeSegment.getLength()).append(";\n");
        });
        return csv.toString();
    }
}
