package gpx;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GpxData {
    private LocalDate date;
    private LocalTime time;

    private ArrayList<Track> tracks = new ArrayList<>();

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void addTrack(Track track){
        tracks.add(track);
    }

    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public ArrayList<Track> getTracks() {
        return tracks;
    }
    public Track getTrack(int trackIndex){
        return tracks.get(trackIndex);
    }

    public void unifyTracks(){
        for(int i = 1; i < tracks.size(); i++){
            tracks.get(0).append(tracks.get(i));
            tracks.remove(i);
        }
    }

    public void smooth(){}

    public String toCSV(int trackIndex){
        return tracks.get(trackIndex).toCSV(); 
    }
}
