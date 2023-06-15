package analysis.difficulty;

import analysis.Math.slopes.SlopeData;

/**
 * Utility class for rating the difficulty of a track based on slope data and track length.
 */
public class DifficultyRater {

    /**
     * Rates the difficulty of a track based on slope data and track length.
     *
     * @param slopeData   The slope data of the track.
     * @param trackLength The length of the track in meters.
     * @return The difficulty rating of the track.
     */
    public static Difficulty rateDifficulty(SlopeData slopeData, double trackLength) {
        // Weights to adjust how important different elements are for the final rating of the track
        double steepness_value = 1; // Steepness of the ascents in the track
        double gradiant_value = 1;  // Overall gradient of the track
        double slope_value = 1;     // Value based on slope characteristics
        double trackLength_value = 1; // Value based on track length
        double relaxation_value = 1; // Value based on relaxation between ascents and descents
        double track_value_final = 0; // Final difficulty rating of the track
        double steepness_resolution = 20.0; // Resolution for calculating steepness
        int count_number_of_ascents = 0; // Number of ascents in the track

        trackLength_value = Math.max(Math.min((trackLength / 10000) - 1, 7), 1);

        for (int i = 0; i < slopeData.getNumberOfSlopeSegments() - 1; i++) {
            if (slopeData.getSlopeSegment(i).getSlope() > 0) {
                // Calculate steepness value based on the length of the ascent
                double slopelength = slopeData.getSlopeSegment(i).getLength() / steepness_resolution;
                steepness_value = Math.min(slopelength + 1, 6);

                if (slopeData.getSlopeSegment(i + 1).getSlope() <= 0) {
                    // Adjust steepness value based on the length of the following descent
                    int nextslopelength = (int) (slopeData.getSlopeSegment(i + 1).getLength() / steepness_resolution);
                    steepness_value = Math.min(Math.max(nextslopelength - 1, 1), 4);
                }

                // Update steepness and relaxation values based on the slope of the ascent and descent
                steepness_value *= (slopeData.getSlopeSegment(i).getSlope() + 0.9);
                relaxation_value *= (slopeData.getSlopeSegment(i + 1).getSlope() * (-1) + 0.9);
                gradiant_value += (steepness_value / relaxation_value);

                count_number_of_ascents++;
            }
        }

        // Calculate the average slope value
        slope_value = gradiant_value / count_number_of_ascents;

        // Calculate the final difficulty rating by combining slope value and track length value
        track_value_final = slope_value + trackLength_value;

        // Determine the difficulty rating based on the final track value
        if (Math.abs(trackLength_value - slope_value) > 7) {
            return Difficulty.Professional;
        }
        if (Math.abs(trackLength_value - slope_value) > 4) {
            return Difficulty.Advanced;
        }
        if (track_value_final < 4) {
            return Difficulty.Beginner;
        }
        if (track_value_final < 8) {
            return Difficulty.Intermediate;
        }
        if (track_value_final < 13) {
            return Difficulty.Advanced;
        }
        return Difficulty.Professional;
    }
}
