package analysis.Math.slopes;

import java.util.ArrayList;

/**
 * Represents slope data consisting of multiple slope segments.
 */
public class SlopeData {
    private ArrayList<SlopeSegment> slopeSegments = new ArrayList<>();

    /**
     * Adds a slope segment to the slope data.
     *
     * @param slopeSegment The slope segment to be added.
     */
    public void addSlopeSegment(SlopeSegment slopeSegment) {
        slopeSegments.add(slopeSegment);
    }

    /**
     * Retrieves the slope segment at the specified index.
     *
     * @param index The index of the slope segment to retrieve.
     * @return The slope segment at the specified index.
     */
    public SlopeSegment getSlopeSegment(int index) {
        return slopeSegments.get(index);
    }

    /**
     * Gets the number of slope segments in the slope data.
     *
     * @return The number of slope segments.
     */
    public int getNumberOfSlopeSegments() {
        return slopeSegments.size();
    }

    /**
     * Prints the type of each slope segment.
     */
    public void printSlopeType() {
        for (int i = 0; i < slopeSegments.size(); i++) {
            if (slopeSegments.get(i).getSlope() > 0)
                System.out.println("Rising for " + slopeSegments.get(i).getLength() / 1000 + "km with a slope of " + slopeSegments.get(i).getSlope() + "m/km.");
            else if (slopeSegments.get(i).getSlope() < 0)
                System.out.println("Falling for " + slopeSegments.get(i).getLength() / 1000 + "km with a slope of " + slopeSegments.get(i).getSlope() + "m/km.");
            else
                System.out.println("Flat for " + slopeSegments.get(i).getLength() / 1000 + "km with a slope of " + slopeSegments.get(i).getSlope() + "m/km.");
        }
    }

    /**
     * Converts the slope data to a CSV string.
     *
     * @return The CSV representation of the slope data.
     */
    public String toCSV() {
        StringBuilder csv = new StringBuilder();
        slopeSegments.forEach(slopeSegment -> {
            csv.append(slopeSegment.getSlope()).append(",").append(slopeSegment.getLength()).append(";\n");
        });
        return csv.toString();
    }
}