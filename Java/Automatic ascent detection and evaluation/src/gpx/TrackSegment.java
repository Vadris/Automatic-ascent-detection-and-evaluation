package gpx;

import java.util.ArrayList;

public class TrackSegment {
    private ArrayList<TrackPoint> trackPoints = new ArrayList<>();

    public void addTrackPoint(TrackPoint trackPoint){
        trackPoints.add(trackPoint);
    }
    public ArrayList<TrackPoint> getTrackPoints() {
        return trackPoints;
    }
    public void append(TrackSegment trackSegment){
        this.trackPoints.addAll(trackSegment.trackPoints);
    }

    public String toCSV(){
        StringBuilder csv = new StringBuilder();
        trackPoints.forEach(trackPoint -> {
            csv.append(trackPoint.getLatitude()).append(",").append(trackPoint.getLongitude()).append(",").append(trackPoint.getElevation()).append(";\n");
        });
        return csv.toString();
    }
}
