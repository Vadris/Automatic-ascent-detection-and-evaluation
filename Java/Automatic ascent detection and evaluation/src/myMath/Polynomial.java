package myMath;

import java.util.Arrays;

import org.python.antlr.PythonParser.return_stmt_return;

public class Polynomial {
    private double[] coeffecients;

    public Polynomial(double... coeffecient){
        coeffecients = coeffecient;
    }

    public Polynomial(DataPoint2D[] interpolationData){
        coeffecients = calculateCoeefecients(interpolationData);
    }

    public double eval(double x){
        double result = 0;
        for(int i = 0; i < coeffecients.length; i++){
            result += coeffecients[i]*Math.pow(x,i);
        }
        return result;
    }

    public Polynomial calculateDerivativ(){
        double[] derivative = new double[coeffecients.length - 1];
        for(int i = 0; i < derivative.length; i++){
            derivative[i] = coeffecients[i + 1]*(i+1);
        }
        return new Polynomial(derivative);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = coeffecients.length - 1; i >= 2; i--){
            if(coeffecients[i] != 0) sb.append(coeffecients[i]).append("*x").append(i).append("+");
        }
        if(coeffecients[1] != 0) sb.append(coeffecients[1]).append("*x");
        if(coeffecients[0] != 0) sb.append("+").append(coeffecients[0]);
        return sb.toString();
    }

    private double denominator(int i, DataPoint2D[] interpolationData){
        double result = 1;
        double x_i = interpolationData[i].getxValue();
        for(int j = interpolationData.length - 1; j >= 0; j--){
            if(i != j){
                result *= x_i - interpolationData[j].getxValue();
            }
        }
        return result;
    }

    private double[] interpolationPolynomial(int i, DataPoint2D[] interpolationData){
        double[] coeffecients = new double[interpolationData.length];
        coeffecients[0] = 1/denominator(i, interpolationData);
        double[] newCoeffecients;
        for(int k = 0; k < interpolationData.length; k++){
            if(k == i) continue;
            newCoeffecients = new double[interpolationData.length];
            for(int j = (k < i) ? (k) : k - 1; j >= 0; j--){
                newCoeffecients[j+1] += coeffecients[j];
                newCoeffecients[j] -= interpolationData[k].getxValue()*coeffecients[j];
            }
            coeffecients = Arrays.copyOf(newCoeffecients, newCoeffecients.length);
        }
        return coeffecients;
    }

    private double[] calculateCoeefecients(DataPoint2D[] interpolationData){
        double[] polynomial = new double[interpolationData.length];
        double[] coeffecients;
        for(int i = 0; i < interpolationData.length; ++i){
            coeffecients = interpolationPolynomial(i, interpolationData);
            for(int k = 0; k < interpolationData.length; ++k){
                polynomial[k] += interpolationData[i].getyValue()*coeffecients[k];
            }
        }
        for(int i = polynomial.length - 1; i >= 0; i--){
            polynomial[i] = round(polynomial[i], 3);
            System.out.println(polynomial[i] + "*x^" + i);
        }
        return polynomial;
    }

    //TODO: Move funciton to proper class
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public double findRootsNewton(double start, double precision){
        double xold = start;
        double xnew = 0;
        Polynomial derivative = calculateDerivativ();
        int i = 0;
        while(true){
            xnew = (xold - (this.eval(xold)/(derivative.eval(xold))));
            if(Math.abs(xnew/xold) <=precision + 1 && xnew>xold) break;
            if(Math.abs(xold/xnew) <= precision + 1 && xold>xnew) break;
            xold = xnew;
            i++;
        }
        System.out.println(i);
        return xnew;
    }
}
