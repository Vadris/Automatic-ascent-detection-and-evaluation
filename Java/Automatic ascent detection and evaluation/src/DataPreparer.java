import java.io.IOException;

import analysis.Math.DataPoint;
import analysis.Math.ElevationProfile;
import data.gpx.GpxData;
import data.gpx.parser.GpxParseException;
import data.gpx.parser.GpxParser;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

public class DataPreparer {
    /**
     * Entry point of the application.
     *
     * @param args The command line arguments.
     * @throws GpxParseException If an error occurs while parsing the GPX data.
     * @throws IOException
     */
    public static void main(String[] args) throws GpxParseException, IOException {
        convertRawData();
        evenSpacingInRawData();
    }

    /**
     * Converts raw GPX data to CSV files.
     *
     * This method reads raw GPX data files, converts them to elevation profiles,
     * and saves them as CSV files.
     *
     * @throws GpxParseException If an error occurs while parsing the GPX data.
     *
     */
    public static void convertRawData() throws GpxParseException {
        double totalDistance = 0;
        for (int i = 1; i <= 21; i++) {
            // Parse GPX data from the file
            GpxData data = GpxParser.parse("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/TourDeFrance2022/stage-" + i + "-parcours.gpx");
            // Convert track data to CSV format
            String csvData = DataPoint.convertPointListToCSV(data.getTrack(0).generateDistanceVsHeightValues());
            // Save the CSV data to a file
            CSVFileWriter.saveDataToCsvFile(csvData, "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw", "raw" + i + ".csv");
        
            totalDistance += data.getTrack(0).calculateTotalDistance();
        }
        System.out.println(totalDistance);
    }

    /**
     * Smooths the raw data.
     *
     * This method applies a smoothing algorithm to the raw data.
     * @throws IOException
     */
    public static void smoothRawData() throws IOException {
        for(int i = 1; i <= 21; i++){
            ElevationProfile profile = ElevationDataParser.parseCsvToElevationProfile("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw" + i + ".csv");
            profile.smooth(50, 1.5);
            profile.smooth(10, 1.7);
            CSVFileWriter.saveDataToCsvFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothed", "smoothed-v2-" + i + ".csv");
        }
    }

    public static void evenSpacingInRawData() throws IOException{
         for(int i = 1; i <= 21; i++){
            ElevationProfile profile = ElevationDataParser.parseCsvToElevationProfile("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw" + i + ".csv");
            profile.spacePointsEvenly(100);
            CSVFileWriter.saveDataToCsvFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/spacingTest",
             "even" + i + ".csv");
        }
    }
}
