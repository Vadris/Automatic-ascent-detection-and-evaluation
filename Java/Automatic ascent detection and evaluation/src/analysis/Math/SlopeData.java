package analysis.Math;

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
}
