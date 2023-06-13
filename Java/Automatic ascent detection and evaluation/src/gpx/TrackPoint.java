package gpx;
/**
 * Represents a Point on the recordet track in polar coordinates
 * @author Fynn Jansen
 * @version 1.1
 */
public class TrackPoint {
    private final static double earthRadius = 6317000; //Earth radius in
    
    private double latitude;
    private double longitude;
    private double elevation;

    /**
     * 
     * @param latitude The latitude in radians
     * @param longitude The longitude in radians
     * @param elevation The elevation in
     */
    public TrackPoint(double latitude, double longitude, double elevation){
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }
    /**
     * 
     * @return The latitude of the point in radians
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * 
     * @return The longitude of the point in radians
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * 
     * @return The elevation in
     */
    public double getElevation() {
        return elevation;
    }
    public double calculateDistanceTo(TrackPoint point){
        double lat1 = this.latitude;
        double lat2 = point.latitude;
        double lon1 = this.longitude;
        double lon2 = point.longitude;

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a =  Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + 
                    Math.cos(lat1) * Math.cos(lat2) *
                    Math.sin(deltaLon/2) * Math.sin(deltaLon/ 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }
}
