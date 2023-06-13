package gpx;

import java.util.ArrayList;

import myMath.GraphPoint;
/**
 * Represents a GPX Track as a list of track segments
 * @author Fynn Jansen
 * @version 1.1
 */
public class Track {
    private ArrayList<TrackSegment> trackSegments = new ArrayList<>();
    /**
     * Adds a track segment to the track
     * @param trackSegment The track segment to be added
     */
    public void addTrackSegment(TrackSegment trackSegment){
        trackSegments.add(trackSegment);
    }
    /**
     * 
     * @return all the track segments of the Track as an ArrayList
     */
    public ArrayList<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    /**
     * Combines all track segments of the Track
     */
    public void unifyTrackSegments(){
        for(int i = 1; i < trackSegments.size(); i++){
            trackSegments.get(0).append(trackSegments.get(i));
            trackSegments.remove(i);
        }
    }

    /**
     * Appends the tracks tracksegments with the track segments of another Track
     * @param track the Track of which tracksegments 
     */
    public void append(Track track){
        trackSegments.addAll(track.trackSegments);
    }

    /**
     * Converts all the track points of the track to CSV in the format 'latitude','longitude','elevation';
     * @return
     */
    public String toCSV(){
        StringBuilder csv = new StringBuilder();
        trackSegments.forEach(trackSegment -> {
            csv.append(trackSegment.toCSV());
        });
        return csv.toString();
    }
    public double calculateTotalDistance(){
        double totalDistance = 0;
        for(int i = 0; i < trackSegments.size(); i++){
            totalDistance += trackSegments.get(i).calculateTotalDistance();
        }
        return totalDistance;
    }
    public ArrayList<GraphPoint> generateDistanceVsHeightValues(){
        ArrayList<GraphPoint> dataPoints = new ArrayList<>();
        for(int i = 0; i < trackSegments.size(); i++){
            dataPoints.addAll(trackSegments.get(i).generateDistanceVsHeightValues());
        }
        return dataPoints;
    }
}
