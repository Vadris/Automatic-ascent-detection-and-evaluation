import java.io.File;

import javax.sound.sampled.SourceDataLine;

import csv.CSVSaver;
import csv.CsvParser;
import gpx.GpxData;
import gpx.parser.GpxParseException;
import gpx.parser.GpxParser;
import myMath.DataPoint2D;
import myMath.ElevationProfile;
import myMath.Polynomial;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        /** 
        GpxData testData = GpxParser.parse("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-4-parcours.gpx");
        ElevationProfile profile = new ElevationProfile(testData.getTrack(0).generateDistanceVsHeightValues());
        profile.smooth(200);
        CSVSaver.saveFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv", 
        "smoothedData4-anders3.csv");
        **/
        CsvParser.parseCsv("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw6.csv");
        DataPoint2D[] interpolDataPoints = new DataPoint2D[4];
        interpolDataPoints[0] = new DataPoint2D(1, -0.9);
        interpolDataPoints[1] = new DataPoint2D(2, -2.1);
        interpolDataPoints[2] = new DataPoint2D(3, -2.5);
        interpolDataPoints[3] = new DataPoint2D(4, -1.5);

        Polynomial polynomial = new Polynomial(interpolDataPoints);
        System.out.println(polynomial.toString());
        
        //CSVSaver.saveFile(testProfile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv",
        // "filterTest1.csv");
    }
}