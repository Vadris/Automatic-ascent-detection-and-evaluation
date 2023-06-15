package data.gpx;

import analysis.Math.DataPoint;

import java.util.ArrayList;

/**
 * Represents a GPX track segment as a list of coordinates in polar form.
 * 
 * <p>A track segment is a collection of track points that form a continuous section of a GPX track. This class provides
 * methods to add track points, retrieve the track points, append another track segment, convert the segment to CSV format,
 * calculate the total distance of the segment, and generate distance vs. height values.</p>
 */
public class TrackSegment {
    private ArrayList<TrackPoint> trackPoints = new ArrayList<>();

    /**
     * Adds a track point to the track segment.
     * 
     * @param trackPoint The track point to be added.
     */
    public void addTrackPoint(TrackPoint trackPoint) {
        trackPoints.add(trackPoint);
    }

    /**
     * Returns all the track points of the segment as an ArrayList.
     * 
     * @return The track points of the segment.
     */
    public ArrayList<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    /**
     * Appends another track segment to the current track segment.
     * 
     * @param trackSegment The track segment to be appended.
     */
    public void append(TrackSegment trackSegment) {
        this.trackPoints.addAll(trackSegment.trackPoints);
    }

    /**
     * Converts the track points of the segment to CSV format in the format 'latitude','longitude','elevation'.
     * 
     * @return The track points as CSV.
     */
    public String toCSV() {
        StringBuilder csv = new StringBuilder();
        trackPoints.forEach(trackPoint -> {
            csv.append(trackPoint.getLatitude()).append(",").append(trackPoint.getLongitude()).append(",")
                    .append(trackPoint.getElevation()).append(";\n");
        });
        return csv.toString();
    }

    /**
     * Calculates the total distance covered by the track segment.
     * 
     * @return The total distance covered by the track segment in meters.
     */
    public double calculateTotalDistance() {
        double totalDistance = 0;
        for (int i = 0; i < trackPoints.size() - 1; i++) {
            totalDistance += trackPoints.get(i).calculateDistanceTo(trackPoints.get(i + 1));
        }
        return totalDistance;
    }

    /**
     * Generates distance vs. height values for the track segment.
     * 
     * @return An ArrayList of DataPoint2D objects representing the distance vs. height values.
     */
    public ArrayList<DataPoint> generateDistanceVsHeightValues() {
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        double distance = 0;
        for (int i = 0; i < trackPoints.size() - 1; i++) {
            dataPoints.add(new DataPoint(distance, trackPoints.get(i).getElevation()));
            distance += trackPoints.get(i).calculateDistanceTo(trackPoints.get(i + 1));
        }
        return dataPoints;
    }
}