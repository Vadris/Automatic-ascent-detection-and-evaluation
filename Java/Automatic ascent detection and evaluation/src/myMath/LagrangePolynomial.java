package myMath;

import java.util.ArrayList;

import org.python.antlr.ast.Continue;

public class LagrangePolynomial {
    private double[] xNodes;
    private double[] yNodes;

    public LagrangePolynomial(double[] xNodes, double[] yNodes){
        this.xNodes = xNodes;
        this.yNodes = yNodes;
    }

    public LagrangePolynomial(ArrayList<GraphPoint> dataPoints){
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

    public double eval(double x){
        double sum = 0;
        for(int i = 0; i <= getDegree(); i++){
            double mul = lProduct(i, x);
            sum += yNodes[i]*mul;
        }
        return sum;
    }
    public double[] getCoeffecients(){
        double[] coeffecients = new double[xNodes.length];
        for(int i = 0; i < getDegree(); i++){
            for(int j = 0; j < getDegree(); j++){
                coeffecients[j] += yNodes[i] * xNodes[j];
            }
        }
        return coeffecients;
    }

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

    private double lProduct(int i, double x){
        double mul = 1;
        for(int j = 0; j <= getDegree(); j++){
            if(j != i){
                mul *= (x - xNodes[j])/(xNodes[i] - xNodes[j]);
            }
        }
        return mul;
    }

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
    
}
