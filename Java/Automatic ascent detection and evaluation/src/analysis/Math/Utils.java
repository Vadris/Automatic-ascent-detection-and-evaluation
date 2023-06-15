package analysis.Math;

/**
 * Utility class containing mathematical helper methods.
 */
public class Utils {
    /**
     * Rounds a given value to the specified number of decimal places.
     *
     * @param value  The value to round.
     * @param places The number of decimal places to round to.
     * @return The rounded value.
     * @throws IllegalArgumentException if the number of decimal places is negative.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Number of decimal places cannot be negative");
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Checks if two given values are approximately equal up to a specified number of decimal places.
     *
     * @param a      The first value.
     * @param b      The second value.
     * @param places The number of decimal places to consider for equality.
     * @return {@code true} if the values are approximately equal, {@code false} otherwise.
     */
    public static boolean isApproxEqual(double a, double b, int places) {
        return round(a, places) == round(b, places);
    }
}
