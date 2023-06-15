package analysis.difficulty;

import analysis.Math.slopes.SlopeData;

public class DifficultyRater{
    public static Difficulty rateDifficulty(SlopeData slopeData, double trackLength){
        //Weights to adjust how important different elements are for the final rating of the Track
        double steepnessWeight = 1; //Steepness of the Ascents in the Track
        double steepness=1;
        double gradiantWeight = 1;
        double gradiant=0;
        double slopeWeight = 1;
        double slope=1;
        double trackLengthWeight = 1;
        double tracklength=1;
        double relaxationWeight = 1;
        double relaxation=1;        
        double trackWeight=0;
        
        int count = 0;
        
        if(trackLength < 20000){
            tracklength=1*trackLengthWeight;
        } else if(trackLength<30000){
            tracklength=2*trackLengthWeight;
        } else if(trackLength<40000){
            tracklength=3*trackLengthWeight;
        } else if(trackLength<50000){
            tracklength=4*trackLengthWeight;
        } else if(trackLength<70000){
            tracklength=5*trackLengthWeight;
        } else if(trackLength<100000){
            tracklength=6*trackLengthWeight;
        } else{
            tracklength=7*trackLengthWeight;
        }
        
        
        for(int i = 0; i < slopeData.getNumberOfSlopeSegments() - 1; i++){
            if (slopeData.getSlopeSegment(i).getSlope()>0){
                if (slopeData.getSlopeSegment(i).getLength()<20){
                    steepness = 1*steepnessWeight;
                }
                else{
                    if(slopeData.getSlopeSegment(i).getLength()<40){
                        steepness=2*steepnessWeight;
                    }
                    else{
                        if(slopeData.getSlopeSegment(i).getLength()<60){
                            steepness=3*steepnessWeight;
                        }
                        else {
                            if(slopeData.getSlopeSegment(i).getLength()<80){
                                steepness=4*steepnessWeight;
                            }
                            else{
                                if(slopeData.getSlopeSegment(i).getLength()<100){
                                    steepness=5*steepnessWeight;
                                }
                                else{
                                    steepness=6*steepnessWeight;
                                }
                            }
                        }
                    }
                }
                steepnessWeight*=(slopeData.getSlopeSegment(i).getSlope()+0.9);
                if (!(slopeData.getSlopeSegment(i+1).getSlope()>=0)){
                    if (slopeData.getSlopeSegment(i+1).getLength()<60){
                        relaxation=1*relaxationWeight;
                    }
                    else{
                        if(slopeData.getSlopeSegment(i+1).getLength()<80){
                            relaxation=2*relaxationWeight;
                        }
                        else{
                            if(slopeData.getSlopeSegment(i+1).getLength()<100){
                                relaxation=3*relaxationWeight;
                            }
                            else{
                                relaxation=4*relaxationWeight;
                            }
                        }
                    }
                    relaxation*=(slopeData.getSlopeSegment(i+1).getSlope()*(-1)+0.9);
                }
                gradiant+=(steepness/relaxation);

                count++;
            }
            
        }
        slope=(gradiant/count)*slopeWeight;
        trackWeight=slope+trackLength;

        if (Math.abs(trackLength-slope) > 7) return Difficulty.Professional;
        if (Math.abs(trackLength-slope)> 4) return Difficulty.Advanced;
        if (trackWeight < 4) return Difficulty.Beginner;
        if (trackWeight< 8) return Difficulty.Intermediate;
        if (trackWeight < 13) return Difficulty.Advanced;
        return Difficulty.Professional;
    }
}
