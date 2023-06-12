package gpx;

public class TrackPoint {
    private double latitude;
    private double longitude;
    private double elevation;

    public TrackPoint(double latitude, double longitude, double elevation){
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getElevation() {
        return elevation;
    }

}
