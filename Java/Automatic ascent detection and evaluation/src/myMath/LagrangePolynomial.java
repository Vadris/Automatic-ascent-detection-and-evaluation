package myMath;

import java.util.ArrayList;
import java.util.Arrays;

import org.python.antlr.PythonParser.return_stmt_return;

public class LagrangePolynomial {
    private double[] xNodes;
    private double[] yNodes;

    public LagrangePolynomial(double[] xNodes, double[] yNodes){
        this.xNodes = xNodes;
        this.yNodes = yNodes;
    }

    public LagrangePolynomial(ArrayList<DataPoint2D> dataPoints){
        xNodes = new double[dataPoints.size()];
        yNodes = new double[dataPoints.size()];
        for(int i = 0; i < dataPoints.size(); i++){
            xNodes[i] = dataPoints.get(i).getxValue();
            yNodes[i] = dataPoints.get(i).getyValue();
        }
    }

    public int getDegree(){
        return xNodes.length - 1;
    }

    public double denominator(int i){
        double result = 1;
        double x_i = xNodes[i];
        for(int j = xNodes.length - 1; j >= 0; j--){
            if(i != j){
                result *= x_i - xNodes[j];
            }
        }
        return result;
    }

    public double[] interpolationPolynomial(int i){
        double[] coeffecients = new double[xNodes.length];
        coeffecients[0] = 1/denominator(i);
        double[] newCoeffecients;
        for(int k = 0; k < xNodes.length; k++){
            if(k == i) continue;
            newCoeffecients = new double[xNodes.length];
            for(int j = (k < i) ? (k) : k - 1; j >= 0; j--){
                newCoeffecients[j+1] += coeffecients[j];
                newCoeffecients[j] -= xNodes[k]*coeffecients[j];
            }
            coeffecients = Arrays.copyOf(newCoeffecients, newCoeffecients.length);
        }
        return coeffecients;
    }

    public double[] calculateCoeefecients(){
        double[] polynomial = new double[xNodes.length];
        double[] coeffecients;
        for(int i = 0; i < xNodes.length; ++i){
            coeffecients = interpolationPolynomial(i);
            for(int k = 0; k < xNodes.length; ++k){
                polynomial[k] += yNodes[i]*coeffecients[k];
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


    public double eval(double x){
        double sum = 0;
        for(int i = 0; i <= getDegree(); i++){
            double mul = lProduct(i, x);
            sum += yNodes[i]*mul;
        }
        return sum;
    }
    
    /**
    public double evalFirstDErivative(double x){
        double sum = 0;
        for(int i = 0; i <= getDegree(); i++){
            double mul = lProduct(i, x);
            double derevsum = 0;
            for(int j = 0; j <= getDegree(); j++){
                if(j != i && (x - xNodes[j]) != 0){
                    derevsum += 1/(x - xNodes[j]);
                }
            }
            sum += yNodes[i]*mul*derevsum;
        }
        return sum;
    }

    public double evalSecondDerivative(double x){
        double sum = 0;
        for(int i = 0; i <= getDegree(); i++){
            double mul = lProduct(i, x);
            double derevsum1 = 0;
            for(int j = 0; j <= getDegree(); j++){
                if(j != i){
                    derevsum1 += 1/(x - xNodes[j]);
                }
            }
            double derevsum2 = 0;
            for(int j = 0; j <= getDegree(); j++){
                if(j != i){
                    derevsum2 += 1/Math.pow(x - xNodes[j], 2);
                }
            }
            double derevsum = Math.pow(derevsum1, 2) - derevsum2;
            sum += yNodes[i]*mul*derevsum;
        }
        return sum;
    }
    **/

    private double lProduct(int i, double x){
        double mul = 1;
        for(int j = 0; j <= getDegree(); j++){
            if(j != i){
                mul *= (x - xNodes[j])/(xNodes[i] - xNodes[j]);
            }
        }
        return mul;
    }

    /**
    public double findZeroNewton(double initialGuess){
        double precision = 1.001;

        boolean deviation = false;
        double xold = initialGuess;
        double xnew = 0;
        while(!deviation){
            //System.out.println(eval(xold));
            //System.out.println(evalFirstDErivative(xold));
            xnew = (xold - (this.eval(xold)/this.evalFirstDErivative(xold)));
            if(xnew/xold <= precision && xnew>xold) deviation = true;
            if(xold/xnew <= precision && xold>xnew) deviation = true;
            //System.out.println(xnew);
            xold = xnew;
        }
        return xnew;
    }
    **/
    
}
