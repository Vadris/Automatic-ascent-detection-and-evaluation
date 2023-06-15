package analysis.difficulty;

public enum Difficulty {
    Beginner,
    Intermediate,
    Advanced,
    Professional;
    @Override
    public String toString(){
        return switch (this) {
            case Beginner -> "Beginner";
            case Intermediate -> "Intermediate";
            case Advanced -> "Advanced";
            case Professional -> "Professional";
        };
    }
}
