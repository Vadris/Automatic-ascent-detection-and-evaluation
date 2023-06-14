import analysis.Math.ElevationProfile;
import data.gpx.parser.GpxParseException;
import data.io.ElevationDataParser;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        /** 
        GpxData testData = GpxParser.parse("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-4-parcours.gpx");
        ElevationProfile profile = new ElevationProfile(testData.getTrack(0).generateDistanceVsHeightValues());
        profile.smooth(200);
        CSVSaver.saveFile(profile.toCSV(), "/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv", 
        "smoothedData4-anders3.csv");
        **/
        ElevationProfile profile = ElevationDataParser.parseCsvToElevationProfile("/home/fynn/Documents/Automatic ascent detection and evaluation/Automatic-ascent-detection-and-evaluation/data/csv/raw/raw6.csv");
        profile.smooth(50, 1.5);
        profile.filterVertical(1000);
        //profile.smooth(10, 1.7);
        profile.calculateSlope().forEach(System.out::println);
        profile.calculateSlope().forEach(slope -> {
            if(slope > 0){
                System.out.println(slope);
            }
        });
    }
}