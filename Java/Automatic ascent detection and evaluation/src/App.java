import java.io.File;

import org.python.util.PythonInterpreter;

import gpx.GpxData;
import gpx.TrackPoint;
import gpx.parser.GpxParseException;
import gpx.parser.GpxParser;
import myMath.GraphPoint;
import myMath.LagrangePolynomial;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        //GpxData test = GpxParser.parse(new File("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-2-parcours.gpx"));
        //CSVSaver.saveFile(test.toCSV(0), "/home/fynn/Documents/CAMMP Week Java/data/csv", "test1.csv");
        //CSVSaver.saveFile(GraphPoint.convertPointListToCSV(test.getTrack(0).generateDistanceVsHeightValues()), "/home/fynn/Documents/CAMMP Week Java/data/csv", "test-height-data1-fixed.csv");
        LagrangePolynomial testPolynomial = new LagrangePolynomial(new double[]{1,2,3}, new double[]{1,5,11});
        /**
        System.out.println(testPolynomial.eval(10));
        System.out.println(testPolynomial.eval(-1));
        System.out.println(testPolynomial.evalFirstDErivative(10));
        System.out.println(testPolynomial.evalFirstDErivative(-1));
        System.out.println(testPolynomial.evalSecondDerivative(10));
        System.out.println(testPolynomial.evalSecondDerivative(-12));
        **/
        double[] coeff = testPolynomial.getCoeffecients();
        for(int i = 0; i < coeff.length; i++){
            System.out.println(coeff[i]);
        }
        //System.out.println(testPolynomial.findZeroNewton(0));
    }
}
