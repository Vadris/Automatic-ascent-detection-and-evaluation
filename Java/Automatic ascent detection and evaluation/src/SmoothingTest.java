import java.io.IOException;

import analysis.Math.ElevationProfile;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

public class SmoothingTest {
    public static void main(String[] args) throws IOException {
        ElevationProfile testProfile = 
        ElevationDataParser.parseCsvToElevationProfile("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw5.csv");
        
        testProfile.smooth(50, 1.5);
        testProfile.smooth(10, 1.7);
        //testProfile.filterVertical(1000);

        CSVFileWriter.saveDataToCsvFile(testProfile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothingTestData",
         "test1.csv");
    }
}
