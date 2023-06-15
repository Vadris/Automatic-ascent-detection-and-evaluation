package analysis.Math.slopes;

public enum SlopeType {
    Rising,
    Flat,
    Falling;

    @Override
    public String toString(){
        switch(this){
            case Rising:
                return "Rising";
            case Flat:
                return "Flat";
            case Falling:
                return "Falling";
            default:
                return "";
        }
    }
}