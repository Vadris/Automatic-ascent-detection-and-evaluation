package myMath;

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
public class DataPoint2D {
    private double xValue;
    private double yValue;

    /**
     * Constructs a new DataPoint2D object with the specified x and y values.
     * 
     * @param xValue The x-value of the data point.
     * @param yValue The y-value of the data point.
     */
    public DataPoint2D(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }
    
    /**
     * Returns the x-value of the data point.
     * 
     * @return The x-value.
     */
    public double getxValue() {
        return xValue;
    }
    
    /**
     * Returns the y-value of the data point.
     * 
     * @return The y-value.
     */
    public double getyValue() {
        return yValue;
    }
    
    /**
     * Sets the x-value of the data point.
     * 
     * @param xValue The new x-value to set.
     */
    public void setxValue(double xValue) {
        this.xValue = xValue;
    }
    
    /**
     * Sets the y-value of the data point.
     * 
     * @param yValue The new y-value to set.
     */
    public void setyValue(double yValue) {
        this.yValue = yValue;
    }
    
    /**
     * Returns a string representation of the data point in the format "xValue,yValue".
     * 
     * @return The string representation of the data point.
     */
    @Override
    public String toString() {
        return xValue + "," + yValue;
    }
    
    /**
     * Converts a list of data points to CSV format.
     * 
     * @param dataPoints The list of data points to convert.
     * @return The data points in CSV format.
     */
    public static String convertPointListToCSV(ArrayList<DataPoint2D> dataPoints) {
        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < dataPoints.size(); i++) {
            csv.append(dataPoints.get(i).getxValue()).append(",").append(dataPoints.get(i).getyValue()).append(";\n");
        }
        return csv.toString();
    }
}
