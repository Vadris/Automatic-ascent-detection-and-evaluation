package analysis.Math.slopes;

/**
 * Enum representing different types of slope.
 */
public enum SlopeType {
    Rising,  // Indicates a rising slope.
    Flat,    // Indicates a flat slope.
    Falling; // Indicates a falling slope.

    /**
     * Returns a string representation of the slope type.
     *
     * @return The string representation of the slope type.
     */
    @Override
    public String toString() {
        return switch (this) {
            case Rising -> "Rising";
            case Flat -> "Flat";
            case Falling -> "Falling";
            default -> "";
        };
    }
}
