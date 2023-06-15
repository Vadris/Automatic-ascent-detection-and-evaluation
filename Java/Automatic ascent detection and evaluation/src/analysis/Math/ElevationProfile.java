package analysis.Math;

import analysis.Math.slopes.SlopeData;
import analysis.Math.slopes.SlopeSegment;

import java.util.ArrayList;

/**
 * Represents an elevation profile with data points and provides various operations on it.
 */
public class ElevationProfile {
    private ArrayList<DataPoint> profile = new ArrayList<>();

    /**
     * Constructs an elevation profile with the specified data points.
     *
     * @param profile The elevation profile data points.
     */
    public ElevationProfile(ArrayList<DataPoint> profile) {
        this.profile = profile;
    }

    /**
     * Smoothes the elevation profile using a moving window averaging approach.
     * The window size determines the number of data points used for the averaging.
     *
     * @param windowSize The size of the moving window.
     * @param steepness  The steepness parameter used in the smoothing algorithm.
     */
    public void smooth(int windowSize, double steepness) {
        for (int i = 0; i < profile.size() - windowSize; i++) {
            // Calculates the mean of the elevation values inside the window
            double meanElevation = 0;
            for (int j = 0; j < windowSize; j++) {
                meanElevation += profile.get(i + j).getY();
            }
            meanElevation /= windowSize;

            for (int j = 0; j < windowSize; j++) {
                double distance = Math.abs(profile.get(i + j).getY() - meanElevation);
                double weight = Math.pow(steepness, -distance);

                if (profile.get(i + j).getY() < meanElevation) {
                    profile.get(i + j).setY(profile.get(i + j).getY() + distance * weight);
                } else if (profile.get(i + j).getY() > meanElevation) {
                    profile.get(i + j).setY(profile.get(i + j).getY() - distance * weight);
                }
            }
        }
    }

    /**
     * Removes unnecessary data points from the elevation profile.
     * This includes removing duplicate points, consecutive points with the same elevation,
     * and averaging the y-values of points with the same x-value.
     */
    public void removeUnnecessaryDataPoints() {
        for (int i = 0; i < profile.size() - 2; i++) {
            DataPoint currentPoint = profile.get(i);
            // Removes duplicate data points
            while (currentPoint.isEqual(profile.get(i + 1))) {
                profile.remove(i + 1);
                i = 0;
            }
            // Removes consecutive points with the same elevation
            while (currentPoint.getY() == profile.get(i + 1).getY() && currentPoint.getY() == profile.get(i + 2).getY()) {
                profile.remove(i + 1);
                i = 0;
            }
            // Averages the y-values of points with the same x-value
            if (currentPoint.getX() == profile.get(i + 1).getX()) {
                double yValueAverage = currentPoint.getY();
                int numberOfAveragingPoints = 1;
                while (currentPoint.getX() == profile.get(i + 1).getX()) {
                    yValueAverage += profile.get(i + 1).getY();
                    profile.remove(i + 1);
                    numberOfAveragingPoints++;
                }
                yValueAverage /= numberOfAveragingPoints;
                profile.get(i).setY(yValueAverage);
                i = 0;
            }
        }

    }

    /**
     * Spaces the data points evenly along the x-axis with the specified spacing.
     *
     * @param spacing The desired spacing between data points.
     */
    public void spacePointsEvenly(double spacing) {
        ArrayList<DataPoint> evenProfile = new ArrayList<>();
        int i = 0;
        for (double currentXValue = profile.get(0).getX(); currentXValue < profile.get(profile.size() - 1).getX(); currentXValue += spacing) {
            while (currentXValue >= profile.get(i).getX())
                i++;
            double m = (profile.get(i).getY() - profile.get(i - 1).getY()) / (profile.get(i).getX() - profile.get(i - 1).getX());
            double b = profile.get(i - 1).getY() - m * profile.get(i - 1).getX();

            evenProfile.add(new DataPoint(currentXValue, m * currentXValue + b));
        }
        profile = evenProfile;
    }

    /**
     * Calculates slope data for the elevation profile.
     *
     * @return The calculated slope data.
     */
    public SlopeData calculateSlopeData() {
        SlopeData slopeData = new SlopeData();
        for (int i = 1; i < profile.size() - 1; i++) {
            double currentSlope = Utils.round((profile.get(i).getY() - profile.get(i - 1).getY()), 2) /
                    Utils.round((profile.get(i).getX() - profile.get(i - 1).getX()) / 1000, 2);

            double nextSlope = Utils.round((profile.get(i + 1).getY() - profile.get(i).getY()), 2) /
                    Utils.round((profile.get(i + 1).getX() - profile.get(i).getX()) / 1000, 2);

            double slopeLength = profile.get(i + 1).getX() - profile.get(i).getX();
            while (Utils.isApproxEqual(currentSlope, nextSlope, 2) && i < profile.size() - 1) {
                slopeLength += profile.get(i + 1).getX() - profile.get(i).getX();
                i++;
            }
            slopeData.addSlopeSegment(new SlopeSegment(currentSlope, slopeLength));
        }
        return slopeData;
    }

    /**
     * Gets the length of the track represented by the elevation profile.
     *
     * @return The track length.
     */
    public double getTrackLength() {
        return profile.get(profile.size() - 1).getX();
    }

    /**
     * Gets the size of the data set in the elevation profile.
     *
     * @return The size of the data set.
     */
    public int getDataSetSize() {
        return profile.size();
    }

    /**
     * Converts the elevation profile to a CSV string.
     *
     * @return The CSV representation of the elevation profile.
     */
    public String toCSV() {
        StringBuilder csv = new StringBuilder();
        profile.forEach(dataPoint -> {
            csv.append(dataPoint.getX()).append(",").append(dataPoint.getY()).append(";\n");
        });
        return csv.toString();
    }
}