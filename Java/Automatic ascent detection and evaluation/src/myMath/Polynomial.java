package myMath;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
    private double[] coefficients;

    /**
     * Constructs a polynomial with the specified coefficients.
     * The coefficients are ordered from highest to lowest degree.
     *
     * @param coefficients The coefficients of the polynomial.
     */
    public Polynomial(double... coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * Constructs a polynomial using interpolation data points.
     * Calculates the coefficients for the polynomial using interpolation.
     *
     * @param interpolationData The interpolation data points.
     */
    public Polynomial(DataPoint2D[] interpolationData) {
        coefficients = calculateCoefficients(interpolationData);
    }

    /**
     * Constructs a polynomial using interpolation data points.
     * Calculates the coefficients for the polynomial using interpolation.
     *
     * @param interpolationData The interpolation data points.
     */
    public Polynomial(ArrayList<DataPoint2D> interpolationData) {
        DataPoint2D[] interpolationDataArray = new DataPoint2D[interpolationData.size()];
        for (int i = 0; i < interpolationData.size(); i++) {
            interpolationDataArray[i] = interpolationData.get(i);
        }
        coefficients = calculateCoefficients(interpolationDataArray);
    }

    /**
     * Evaluates the polynomial for the given x value.
     *
     * @param x The x value to evaluate.
     * @return The result of evaluating the polynomial.
     */
    public double eval(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    /**
     * Calculates the derivative of the polynomial.
     *
     * @return The derivative polynomial.
     */
    public Polynomial calculateDerivative() {
        double[] derivative = new double[coefficients.length - 1];
        for (int i = 0; i < derivative.length; i++) {
            derivative[i] = coefficients[i + 1] * (i + 1);
        }
        return new Polynomial(derivative);
    }

    /**
     * Converts the polynomial to a string representation.
     *
     * @return The string representation of the polynomial.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 2; i--) {
            if (coefficients[i] != 0) {
                sb.append(coefficients[i]).append("*x^").append(i).append("+");
            }
        }
        if (coefficients[1] != 0) {
            sb.append(coefficients[1]).append("*x");
        }
        if (coefficients[0] != 0) {
            sb.append("+").append(coefficients[0]);
        }
        return sb.toString();
    }

    // Helper methods

    /**
     * Calculates the denominator used in interpolation.
     *
     * @param i                The index of the current interpolation data point.
     * @param interpolationData The interpolation data points.
     * @return The denominator value.
     */
    private double denominator(int i, DataPoint2D[] interpolationData) {
        // The denominator represents the product of differences between the x values
        // of the interpolation data points, excluding the current point.
        double result = 1;
        double x_i = interpolationData[i].getxValue();
        for (int j = interpolationData.length - 1; j >= 0; j--) {
            if (i != j) {
                result *= x_i - interpolationData[j].getxValue();
            }
        }
        return result;
    }

    /**
     * Calculates the coefficients for the interpolation polynomial.
     *
     * @param i                The index of the current interpolation data point.
     * @param interpolationData The interpolation data points.
     * @return The coefficients of the interpolation polynomial.
     */
    private double[] interpolationPolynomial(int i, DataPoint2D[] interpolationData) {
        // The interpolation polynomial is calculated by applying Lagrange interpolation.
        // Each term of the polynomial is determined by dividing 1 by the denominator,
        // which is the product of differences between the x values of the interpolation data points.
        double[] coefficients = new double[interpolationData.length];
        coefficients[0] = 1 / denominator(i, interpolationData);
        double[] newCoefficients;
        for (int k = 0; k < interpolationData.length; k++) {
            if (k == i) {
                continue;
            }
            newCoefficients = new double[interpolationData.length];
            for (int j = (k < i) ? (k) : k - 1; j >= 0; j--) {
                newCoefficients[j + 1] += coefficients[j];
                newCoefficients[j] -= interpolationData[k].getxValue() * coefficients[j];
            }
            coefficients = Arrays.copyOf(newCoefficients, newCoefficients.length);
        }
        return coefficients;
    }

    /**
     * Calculates the coefficients of the polynomial using interpolation.
     *
     * @param interpolationData The interpolation data points.
     * @return The coefficients of the polynomial.
     */
    private double[] calculateCoefficients(DataPoint2D[] interpolationData) {
        // The coefficients of the polynomial are calculated by applying interpolation
        // for each interpolation data point and summing the results weighted by the y values.
        double[] polynomial = new double[interpolationData.length];
        double[] coefficients;
        for (int i = 0; i < interpolationData.length; ++i) {
            coefficients = interpolationPolynomial(i, interpolationData);
            for (int k = 0; k < interpolationData.length; ++k) {
                polynomial[k] += interpolationData[i].getyValue() * coefficients[k];
            }
        }
        // Optional rounding of coefficients and printing for debugging purposes
        for (int i = polynomial.length - 1; i >= 0; i--) {
            polynomial[i] = round(polynomial[i], 3);
        }
        return polynomial;
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     *
     * @param value  The value to round.
     * @param places The number of decimal places to round to.
     * @return The rounded value.
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Finds the roots of the polynomial using Newton's method.
     *
     * @param start     The starting value for the root search.
     * @param precision The desired precision of the root.
     * @return The root of the polynomial.
     */
    public double findRootsNewton(double start, double precision) {
        double xOld = start;
        double xNew = 0;
        Polynomial derivative = calculateDerivative();
        int i = 0;
        while (true) {
            xNew = xOld - (this.eval(xOld) / (derivative.eval(xOld)));
            if (Math.abs(xNew - xOld) <= precision + 1) {
                break;
            }
            xOld = xNew;
            i++;
        }
        return xNew;
    }

    /**
     * Converts the polynomial to a CSV representation.
     *
     * @param start The starting x value.
     * @param end   The ending x value.
     * @param step  The step size between x values.
     * @return The polynomial in CSV format.
     */
    public String toCSV(double start, double end, double step) {
        StringBuilder csv = new StringBuilder();
        for (double current = start; current <= end; current += step) {
            csv.append(current).append(",").append(eval(current)).append(";\n");
        }
        return csv.toString();
    }
}