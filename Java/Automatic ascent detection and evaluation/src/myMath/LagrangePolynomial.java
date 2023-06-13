package myMath;

import java.util.ArrayList;

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
            double mul = 1;
            for(int j = 0; j <= getDegree(); j++){
                if(j != i){
                    mul *= (x - xNodes[j])/(xNodes[i] - xNodes[j]);
                }
            }
            sum += yNodes[i]*mul;
        }
        return sum;
    }
}
