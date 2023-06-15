import analysis.Difficulty;
import analysis.Math.DataPoint;
import analysis.Math.ElevationProfile;
import analysis.Math.Polynomial;
import data.gpx.parser.GpxParseException;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        ElevationProfile profile = 
        ElevationDataParser.parseCsvToElevationProfile("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw5.csv");
        profile.removeDuplicatePoints();
        profile.spacePointsEvenly(200);
        CSVFileWriter.saveDataToCsvFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothingTestData",
        "test2.csv");
    }
}