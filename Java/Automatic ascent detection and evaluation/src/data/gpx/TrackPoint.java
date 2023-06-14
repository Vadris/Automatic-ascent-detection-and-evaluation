package data.gpx;

/**
 * Represents a point on the recorded track in polar coordinates.
 * 
 * <p>This class provides information about a specific point on a track. It includes latitude, longitude, and elevation 
 * data, and provides methods to access and calculate distances between points.</p>
 * 
 * <p>Version: 1.1</p>
 * <p>Author: Fynn Jansen</p>
 */
public class TrackPoint {
    private final static double earthRadius = 6317000; // Earth radius in meters
    
    private double latitude;
    private double longitude;
    private double elevation;

    /**
     * Constructs a TrackPoint object with the specified latitude, longitude, and elevation.
     * 
     * @param latitude The latitude of the point in radians.
     * @param longitude The longitude of the point in radians.
     * @param elevation The elevation of the point.
     */
    public TrackPoint(double latitude, double longitude, double elevation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    /**
     * Returns the latitude of the track point.
     * 
     * @return The latitude of the point in radians.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of the track point.
     * 
     * @return The longitude of the point in radians.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the elevation of the track point.
     * 
     * @return The elevation of the point.
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * Calculates the distance between this track point and the specified track point.
     * 
     * @param point The track point to calculate the distance to.
     * @return The distance between this track point and the specified track point in meters.
     */
    public double calculateDistanceTo(TrackPoint point) {
        double lat1 = this.latitude;
        double lat2 = point.latitude;
        double lon1 = this.longitude;
        double lon2 = point.longitude;

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}