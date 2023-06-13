import java.io.File;

import gpx.GpxData;
import gpx.TrackPoint;
import gpx.parser.GpxParseException;
import gpx.parser.GpxParser;
import myMath.GraphPoint;
import myMath.LagrangePolynomial;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        GpxData test = GpxParser.parse(new File("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-2-parcours.gpx"));
        //CSVSaver.saveFile(test.toCSV(0), "/home/fynn/Documents/CAMMP Week Java/data/csv", "test1.csv");
        //CSVSaver.saveFile(GraphPoint.convertPointListToCSV(test.getTrack(0).generateDistanceVsHeightValues()), "/home/fynn/Documents/CAMMP Week Java/data/csv", "test-height-data1-fixed.csv");
        LagrangePolynomial testPolynomial = new LagrangePolynomial(test.getTrack(0).generateDistanceVsHeightValues());
        System.out.println(testPolynomial.eval(10));
        //System.out.println(testPolynomial.eval(-1));
    
    }
}
