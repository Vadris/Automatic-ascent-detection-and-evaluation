import analysis.Math.ElevationProfile;
import data.gpx.parser.GpxParseException;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        ElevationProfile profile = 
        ElevationDataParser.parseCsvToElevationProfile(
        "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw4.csv");
        profile.spacePointsEvenly(1000);
        CSVFileWriter.saveDataToCsvFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothingTestData",
         "vertical-intersect-linear-interpolation.csv");
        
    }
}