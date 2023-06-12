package gpx;

import java.util.ArrayList;

public class Track {
    private ArrayList<TrackSegment> trackSegments = new ArrayList<>();
    public void addTrackSegment(TrackSegment trackSegment){
        trackSegments.add(trackSegment);
    }
    public ArrayList<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    public void unifyTrackSegments(){
        for(int i = 1; i < trackSegments.size(); i++){
            trackSegments.get(0).append(trackSegments.get(i));
            trackSegments.remove(i);
        }
    }

    public void append(Track track){
        trackSegments.addAll(track.trackSegments);
    }

    public String toCSV(){
        StringBuilder csv = new StringBuilder();
        trackSegments.forEach(trackSegment -> {
            csv.append(trackSegment.toCSV());
        });
        return csv.toString();
    }
}
