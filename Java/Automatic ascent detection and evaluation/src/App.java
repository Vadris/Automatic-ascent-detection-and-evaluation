import java.io.File;

import gpx.GpxData;
import gpx.parser.GpxParseException;
import gpx.parser.GpxParser;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        GpxData test = GpxParser.parse(new File("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-1-parcours.gpx"));
        System.out.println(test.toCSV(0));
        System.out.println(test.getDate());
        System.out.println(test.getTime());
    }
}
