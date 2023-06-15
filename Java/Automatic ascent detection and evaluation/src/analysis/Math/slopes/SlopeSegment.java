package analysis.Math.slopes;

/**
 * Represents a segment of a slope with its slope value and length.
 */
public class SlopeSegment {
    double slope;  // The slope value in meters per kilometer.
    double length; // The length of the slope segment in meters.

    /**
     * Constructs a slope segment with the specified slope and length.
     *
     * @param slope  The slope value in meters per kilometer.
     * @param length The length of the slope segment in meters.
     */
    public SlopeSegment(double slope, double length) {
        this.slope = slope;
        this.length = length;
    }

    /**
     * Gets the slope value of the slope segment.
     *
     * @return The slope value in meters per kilometer.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * Gets the length of the slope segment.
     *
     * @return The length of the slope segment in meters.
     */
    public double getLength() {
        return length;
    }
}
