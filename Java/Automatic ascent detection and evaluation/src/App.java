import java.io.File;

import csv.CSVSaver;
import gpx.GpxData;
import gpx.parser.GpxParseException;
import gpx.parser.GpxParser;
import myMath.DataPoint2D;
import myMath.ElevationProfile;
import myMath.Polynomial;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        GpxData testData = GpxParser.parse(new File("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-2-parcours.gpx"));
        ElevationProfile profile = new ElevationProfile(testData.getTrack(0).generateDistanceVsHeightValues());
        profile.smooth(20);
        CSVSaver.saveFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv", "smoothedData1.csv");
    }
}