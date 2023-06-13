package gpx;

import java.util.ArrayList;

import myMath.GraphPoint;
/**
 * Represents a GPX tracksegment as a list of coordinates in polar form
 * @author Fynn Jansen
 * @version 1.1
 */
public class TrackSegment {
    private ArrayList<TrackPoint> trackPoints = new ArrayList<>();

    /**
     * Adds a Point to the track segment
     * @param trackPoint The track point to be added
     */
    public void addTrackPoint(TrackPoint trackPoint){
        trackPoints.add(trackPoint);
    }
    /**
     * Gets all of the track points of the segment as a <code>ArrayList</code>
     * @return 
     */
    public ArrayList<TrackPoint> getTrackPoints() {
        return trackPoints;
    }
    /**
     * Appends the track segment with the points of another track segment
     * @param trackSegment The track segment to be appended
     */
    public void append(TrackSegment trackSegment){
        this.trackPoints.addAll(trackSegment.trackPoints);
    }

    /**
     * Converts the points of the track segment to CSV in the format 'latitude','longitude','elevation'
     * @return The points as CSV
     */
    public String toCSV(){
        StringBuilder csv = new StringBuilder();
        trackPoints.forEach(trackPoint -> {
            csv.append(trackPoint.getLatitude()).append(",").append(trackPoint.getLongitude()).append(",").append(trackPoint.getElevation()).append(";\n");
        });
        return csv.toString();
    }
    public double calculateTotalDistance(){
        double totalDistance = 0;
        for(int i = 0; i < trackPoints.size() - 1; i++){
            totalDistance += trackPoints.get(i).calculateDistanceTo(trackPoints.get(i + 1));
        }
        return totalDistance;
    }
    public ArrayList<GraphPoint> generateDistanceVsHeightValues(){
        ArrayList<GraphPoint> dataPoints = new ArrayList<>();
        double distance = 0;
        for(int i = 0; i < trackPoints.size() - 1; i++){
            dataPoints.add(new GraphPoint(distance, trackPoints.get(i).getElevation()));
            distance += trackPoints.get(i).calculateDistanceTo(trackPoints.get(i + 1));
        }
        return dataPoints;
    }
}
