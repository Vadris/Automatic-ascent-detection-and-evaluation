package analysis.Math;

public class Utils {
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static boolean isApproxEqual(double a, double b, int places){
        return round(a, places) == round(b, places);
    }
}
