import gpx.parser.GpxParseException;
import myMath.LagrangePolynomial;

public class App {
    public static void main(String[] args) throws Exception, GpxParseException {
        //GpxData test = GpxParser.parse(new File("/home/fynn/Documents/CAMMP Week Java/data/TourDeFrance2022/stage-2-parcours.gpx"));
        //CSVSaver.saveFile(test.toCSV(0), "/home/fynn/Documents/CAMMP Week Java/data/csv", "test1.csv");
        //CSVSaver.saveFile(GraphPoint.convertPointListToCSV(test.getTrack(0).generateDistanceVsHeightValues()), "/home/fynn/Documents/CAMMP Week Java/data/csv", "test-height-data1-fixed.csv");
        LagrangePolynomial testPolynomial = new LagrangePolynomial(new double[]{0.25, 0.5, 0.75, 1, 1.25}, new double[]{-0.76, -0.38, 0.52, 2.5, 6.3});
        double[] coeff = testPolynomial.calculateCoeefecients();
        
        //System.out.println(testPolynomial.findZeroNewton(0));
    }
}
