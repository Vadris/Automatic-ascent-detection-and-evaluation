import java.io.IOException;

import csv.CSVSaver;
import csv.CsvParser;
import myMath.ElevationProfile;

public class SmoothingTest {
    public static void main(String[] args) throws IOException {
        ElevationProfile testProfile = 
        CsvParser.parseCsv("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw5.csv");
        
        testProfile.filterVertical(1000);
        testProfile.smooth(50);
        
        CSVSaver.saveFile(testProfile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothingTestData",
         "test1.csv");
    }
}
