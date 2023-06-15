package tests;

import analysis.Math.ElevationProfile;
import analysis.Math.slopes.SlopeData;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

import java.io.IOException;

/**
 * A test class for detecting slopes and saving the slope data to CSV files.
 */
public class SlopeDetectionTest {

    /**
     * Entry point of the slope detection test.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException if an error occurs during file parsing or writing.
     */
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 21; i++) {
            // Parse the elevation data from a CSV file
            ElevationProfile profile = ElevationDataParser.parseCsvToElevationProfile(
                    "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothed/smoothed-v3-" + i + ".csv");

            // Calculate the slope data
            SlopeData slopeData = profile.calculateSlopeData();

            // Save the slope data to a CSV file
            CSVFileWriter.saveDataToCsvFile(slopeData.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/slopeData",
                    "slopes-" + i + ".csv");
        }
    }
}
