import analysis.Math.DifficultyRater;
import analysis.Math.ElevationProfile;
import analysis.Math.SlopeData;
import data.gpx.parser.GpxParseException;
import data.io.CSVFileWriter;
import data.io.ElevationDataParser;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        ElevationProfile profile = 
        ElevationDataParser.parseCsvToElevationProfile(
        "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/smoothed/smoothed-v3-2.csv");
        profile.smooth(10, 1.7);
        SlopeData testData = profile.calculateSlopeData();
        testData.printSlopeType();
        System.out.println();
        System.out.println("There are " + testData.getNumberOfSlopeSegments() + " diffrent Slopes on a distance of " + profile.getTrackLength()/1000 + "km.");
        System.out.println();
        System.out.println(DifficultyRater.rateDifficulty(testData, profile.getTrackLength()));
    }
}