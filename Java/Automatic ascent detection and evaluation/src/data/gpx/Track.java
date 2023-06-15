package data.gpx;

import analysis.Math.DataPoint;

import java.util.ArrayList;

/**
 * Represents a GPX Track as a collection of track segments.
 * 
 * <p>A track consists of multiple track segments, each representing a continuous section of the GPX track. This class provides
 * methods to add track segments, retrieve the track segments, unify the track segments, append another track's segments,
 * convert the track points to CSV format, calculate the total distance of the track, and generate distance vs. height values.</p>
 */
public class Track {
    private ArrayList<TrackSegment> trackSegments = new ArrayList<>();

    /**
     * Adds a track segment to the track.
     * 
     * @param trackSegment The track segment to be added.
     */
    public void addTrackSegment(TrackSegment trackSegment) {
        trackSegments.add(trackSegment);
    }

    /**
     * Returns all the track segments of the track as an ArrayList.
     * 
     * @return The track segments of the track.
     */
    public ArrayList<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    /**
     * Combines all the track segments of the track into a single track segment.
     * This operation modifies the current track object.
     */
    public void unifyTrackSegments() {
        for (int i = 1; i < trackSegments.size(); i++) {
            trackSegments.get(0).append(trackSegments.get(i));
            trackSegments.remove(i);
        }
    }

    /**
     * Appends the track's track segments with the track segments of another track.
     * 
     * @param track The track whose track segments will be appended.
     */
    public void append(Track track) {
        trackSegments.addAll(track.trackSegments);
    }

    /**
     * Converts all the track points of the track to CSV format in the format 'latitude','longitude','elevation'.
     * 
     * @return The track points as CSV.
     */
    public String toCSV() {
        StringBuilder csv = new StringBuilder();
        trackSegments.forEach(trackSegment -> {
            csv.append(trackSegment.toCSV());
        });
        return csv.toString();
    }

    /**
     * Calculates the total distance covered by the track.
     * 
     * @return The total distance covered by the track in meters.
     */
    public double calculateTotalDistance() {
        double totalDistance = 0;
        for (int i = 0; i < trackSegments.size(); i++) {
            totalDistance += trackSegments.get(i).calculateTotalDistance();
        }
        return totalDistance;
    }

    /**
     * Generates distance vs. height values for the track.
     * 
     * @return An ArrayList of DataPoint2D objects representing the distance vs. height values.
     */
    public ArrayList<DataPoint> generateDistanceVsHeightValues() {
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < trackSegments.size(); i++) {
            dataPoints.addAll(trackSegments.get(i).generateDistanceVsHeightValues());
        }
        return dataPoints;
    }
}