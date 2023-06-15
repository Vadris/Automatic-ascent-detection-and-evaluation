package data.gpx;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents GPX data consisting of tracks recorded on a specific date and time.
 * 
 * <p>The GpxData class holds information about the date and time of the recorded tracks and provides methods
 * to add tracks, retrieve the date, time, and tracks, unify multiple tracks into a single track,
 * smooth the tracks (to be implemented), and convert a track to CSV format.</p>
 */
public class GpxData {
    private LocalDate date;
    private LocalTime time;
    private ArrayList<Track> tracks = new ArrayList<>();

    /**
     * Sets the date of the recorded tracks.
     * 
     * @param date The date of the recorded tracks.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the time of the recorded tracks.
     * 
     * @param time The time of the recorded tracks.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Adds a track to the GPX data.
     * 
     * @param track The track to be added.
     */
    public void addTrack(Track track) {
        tracks.add(track);
    }

    /**
     * Returns the date of the recorded tracks.
     * 
     * @return The date of the recorded tracks.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the time of the recorded tracks.
     * 
     * @return The time of the recorded tracks.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns all the tracks in the GPX data as an ArrayList.
     * 
     * @return The tracks in the GPX data.
     */
    public ArrayList<Track> getTracks() {
        return tracks;
    }

    /**
     * Returns the track at the specified index in the GPX data.
     * 
     * @param trackIndex The index of the track to retrieve.
     * @return The track at the specified index.
     */
    public Track getTrack(int trackIndex) {
        return tracks.get(trackIndex);
    }

    /**
     * Combines all the tracks in the GPX data into a single track.
     * This operation modifies the current GpxData object.
     */
    public void unifyTracks() {
        for (int i = 1; i < tracks.size(); i++) {
            tracks.get(0).append(tracks.get(i));
            tracks.remove(i);
        }
    }

    /**
     * Smoothes the recorded tracks.
     * This method is currently not implemented.
     */
    public void smooth() {
        // To be implemented
    }

    /**
     * Converts the track at the specified index to CSV format.
     * 
     * @param trackIndex The index of the track to convert.
     * @return The track in CSV format.
     */
    public String toCSV(int trackIndex) {
        return tracks.get(trackIndex).toCSV();
    }
}