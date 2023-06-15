package tests;

import analysis.Math.ElevationProfile;
import analysis.Math.Utils;
import analysis.difficulty.DifficultyRater;
import data.io.ElevationDataParser;

import java.io.IOException;

/**
 * A test class for testing the  difficulty evaluation of tracks based on elevation data.
 */
public class DifficultyEvaluationTest {

    /**
     * Entry point of the difficulty evaluation test.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException if an error occurs during file parsing.
     */
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 21; i++) {
            // Parse the elevation data from a CSV file
            ElevationProfile profile = ElevationDataParser.parseCsvToElevationProfile(
                    "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothed/smoothed-v3-" + i + ".csv");

            System.out.println("Stage  " + i + ": ");
            // Calculate the slope data and rate the difficulty of the track
            System.out.println(DifficultyRater.rateDifficulty(profile.calculateSlopeData(), profile.getTrackLength()));
            System.out.println("Total distance: " + profile.getTrackLength() / 1000 + "km");
            System.out.println("Height difference: " + Utils.round(profile.getHeightDiffrence(), 2));
            System.out.println("Steepest Slope: " + Utils.round(profile.calculateSlopeData().getSteepestSlope(), 2));
            System.out.println("Total Ascending Distance: " + Utils.round(profile.calculateSlopeData().getTotalAscendingDistance(), 2));
            System.out.println("___________________________________________________________________________________________________");
        }
    }
}
