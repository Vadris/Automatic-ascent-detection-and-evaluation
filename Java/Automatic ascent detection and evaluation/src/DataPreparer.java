import csv.CSVSaver;
import gpx.GpxData;
import gpx.parser.GpxParseException;
import gpx.parser.GpxParser;
import myMath.DataPoint2D;

public class DataPreparer {
    public static void main(String[] args) throws GpxParseException {
        convertRawData();
    }
    public static void convertRawData() throws GpxParseException{
        for(int i = 1; i <= 21; i++){
            GpxData data = GpxParser.parse("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/TourDeFrance2022/stage-" + i + "-parcours.gpx");
            CSVSaver.saveFile(DataPoint2D.convertPointListToCSV(data.getTrack(0).generateDistanceVsHeightValues()), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw", "raw" + i + ".csv");
            
        }
        

    }
}
