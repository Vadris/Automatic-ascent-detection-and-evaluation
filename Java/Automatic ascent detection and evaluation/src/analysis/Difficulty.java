package analysis;

public enum Difficulty {
    Beginner,
    Intermediate,
    Advanced,
    Professional;

    @Override
    public String toString(){
        switch (this) {
            case Beginner:
                return "Beginner";
            case Intermediate:
                return "Intermediate";
            case Advanced:
                return "Advanced";
            case Professional:
                return "Professional";
            default:
                return "";
        }
    }
}
