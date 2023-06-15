package analysis.Math;

import analysis.Difficulty;

public class DifficultyRater{
    public static Difficulty rateDifficulty(SlopeData slopeData, double trackLength){
        //Weights to adjust how important different elements are for the final rating of the Track
        double steepnessWeight = 1; //Steepness of the Ascents in the Track
        double gradiantWeight = 1;  //
        double slopeWeight = 1;
        double trackLengthWeight = 1;
        double relaxationWeight = 1;
        
        double trackWeight=0;
        int count = 0;
        
        
        //ArrayList<Double> xcoor= new ArrayList<Double>();
        //ArrayList<Double> ycoor= new ArrayList<Double>();
        if(trackLength < 20000){
            trackLengthWeight=1;
        } else if(trackLength<30000){
            trackLengthWeight=2;
        } else if(trackLength<40000){
            trackLengthWeight=3;
        } else if(trackLength<50000){
            trackLengthWeight=4;
        } else if(trackLength<70000){
            trackLengthWeight=5;
        } else if(trackLength<100000){
            trackLengthWeight=6;
        } else{
            trackLengthWeight=7;
        }
        
        
        for(int i = 0; i < slopeData.getNumberOfSlopeSegments() - 1; i++){
            if (slopeData.getSlopeSegment(i).getSlope()>0){
                if (slopeData.getSlopeSegment(i).getLength()<20){
                    steepnessWeight = 1;
                }
                else{
                    if(slopeData.getSlopeSegment(i).getLength()<40){
                        steepnessWeight=2;
                    }
                    else{
                        if(slopeData.getSlopeSegment(i).getLength()<60){
                            steepnessWeight=3;
                        }
                        else {
                            if(slopeData.getSlopeSegment(i).getLength()<80){
                                steepnessWeight=4;
                            }
                            else{
                                if(slopeData.getSlopeSegment(i).getLength()<100){
                                    steepnessWeight=5;
                                }
                                else{
                                    steepnessWeight=6;
                                }
                            }
                        }
                    }
                }
                steepnessWeight*=(slopeData.getSlopeSegment(i).getSlope()+0.9);
                if (!(slopeData.getSlopeSegment(i+1).getSlope()>1)){
                    if (slopeData.getSlopeSegment(i+1).getLength()<60){
                        relaxationWeight=1;
                    }
                    else{
                        if(slopeData.getSlopeSegment(i+1).getLength()<80){
                            relaxationWeight=2;
                        }
                        else{
                            if(slopeData.getSlopeSegment(i+1).getLength()<100){
                                relaxationWeight=3;
                            }
                            else{
                                relaxationWeight=4;
                            }
                        }
                    }
                    relaxationWeight*=(slopeData.getSlopeSegment(i+1).getSlope()*(-1)+0.9);
                }
                gradiantWeight+=(steepnessWeight/relaxationWeight);

                count++;
            }
            
        }
        slopeWeight=gradiantWeight/count;
        trackWeight=slopeWeight+trackLengthWeight;
        /*for (int i=1; i>15; i++){
            xcoor.add(i, Double.valueOf(i));
            Random a = new Random();
            double random_double = (double)Math.floor(Math.random() * (30 - (-20) + 1) + (-20));
            ycoor.add(i, random_double);
        }*/
        /*int length=xcoor.size();
        int count=0;
        double averageGradiant=0.0;
        double heightDifference=0.0;
        double difficulty=0.0;
        double difficulty1=0.0;
        for (int i=2; i<length;i++){
            if (ycoor.get(i)<ycoor.get(i-1)){
                heightDifference+=ycoor.get(i)-ycoor.get(i-1);
                averageGradiant+=(ycoor.get(i)-ycoor.get(i-1))/(xcoor.get(i)-xcoor.get(i-1));
                count++;
            }
        }
        averageGradiant/=count;
        if (length < 20000) {
            difficulty += 1;
        } else if (length < 30000) {
            difficulty += 2;
        } else if (length < 40000) {
            difficulty += 3;
        } else if (length < 50000) {
            difficulty += 4;
        } else if (length < 60000) {
            difficulty += 5;
        } else if (length < 80000) {
            difficulty += 6;
        } else if (length < 100000) {
            difficulty += 7;
        } else {
            difficulty += 8;
        }

        if (heightDifference < 100) {
            difficulty1 += 1;
        } else if (heightDifference < 200) {
            difficulty1 += 2;
        } else if (heightDifference < 300) {
            difficulty1 += 3;
        } else if (heightDifference < 500) {
            difficulty1 += 4;
        } else if (heightDifference < 700) {
            difficulty1 += 5;
        } else if (heightDifference < 1000) {
            difficulty1 += 6;
        } else {
            difficulty1 += 7;
        }
*/
        if (Math.abs(trackLengthWeight-slopeWeight) > 7) return Difficulty.Professional;
        if (Math.abs(trackLengthWeight-slopeWeight)> 4) return Difficulty.Advanced;
        if (trackWeight < 4) return Difficulty.Beginner;
        if (trackWeight< 8) return Difficulty.Intermediate;
        if (trackWeight < 13) return Difficulty.Advanced;
        return Difficulty.Professional;
    }
}
