package analysis.Math;

import java.util.ArrayList;

/**
 * Represents a 2D data point with x and y values.
 * 
 * <p>The DataPoint2D class represents a data point in a two-dimensional space,
 * with an x-value and a y-value. It provides methods to retrieve and modify the
 * x and y values, as well as converting a list of data points to CSV format.</p>
 * 
 * <p>Version: 1.0</p>
 * <p>Author: [Your Name]</p>
 */
public class DataPoint {
    private double x;
    private double y;

    /**
     * Constructs a new DataPoint2D object with the specified x and y values.
     * 
     * @param x The x-value of the data point.
     * @param y The y-value of the data point.
     */
    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the x-value of the data point.
     * 
     * @return The x-value.
     */
    public double getX() {
        return x;
    }
    
    /**
     * Returns the y-value of the data point.
     * 
     * @return The y-value.
     */
    public double getY() {
        return y;
    }
    
    /**
     * Sets the x-value of the data point.
     * 
     * @param xValue The new x-value to set.
     */
    public void setX(double xValue) {
        this.x = xValue;
    }
    
    /**
     * Sets the y-value of the data point.
     * 
     * @param yValue The new y-value to set.
     */
    public void setY(double yValue) {
        this.y = yValue;
    }
    
    /**
     * Returns a string representation of the data point in the format "xValue,yValue".
     * 
     * @return The string representation of the data point.
     */
    @Override
    public String toString() {
        return x + "," + y;
    }
    
    /**
     * Converts a list of data points to CSV format.
     * 
     * @param dataPoints The list of data points to convert.
     * @return The data points in CSV format.
     */
    public static String convertPointListToCSV(ArrayList<DataPoint> dataPoints) {
        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < dataPoints.size(); i++) {
            csv.append(dataPoints.get(i).getX()).append(",").append(dataPoints.get(i).getY()).append(";\n");
        }
        return csv.toString();
    }
}
