package Components;

public class Option {
    private final String optionText;
    public Choice choiceDestination;
    public Traits traitScore;
    public Traits traitRequirement;

    public Option(String optionText, Choice choiceDestination, Traits traitScore, Traits traitRequirement) {
        this.optionText = optionText;
        this.choiceDestination = choiceDestination;
        this.traitScore = traitScore;
        this.traitRequirement = traitRequirement;
    }

    public Option(String optionText, Choice choiceDestination) {
        this.optionText = optionText;
        this.choiceDestination = choiceDestination;
        this.traitScore = new Traits(0);
        this.traitRequirement = new Traits(0);
    }


}
