import analysis.Math.ElevationProfile;
import analysis.Math.SlopeData;
import data.gpx.parser.GpxParseException;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        ElevationProfile profile = 
        ElevationDataParser.parseCsvToElevationProfile(
        "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/smoothed/smoothed-v3-6");
        SlopeData testData = profile.calculateSlopeData();
        
    }
}