package Components;

import java.util.HashMap;
public class Option {
    private final String optionText;
    public Choice choiceDestination;
    public HashMap<String, Integer> traitScore;
    public HashMap<String, Integer> traitRequirement;

    public Option(String optionText, Choice choiceDestination, HashMap<String, Integer> traitScore, HashMap<String, Integer> traitRequirement) {
        this.optionText = optionText;
        this.choiceDestination = choiceDestination;
        this.traitScore = traitScore;
        this.traitRequirement =traitRequirement;
    }

    public Option(String optionText, Choice choiceDestination) {
        this.optionText = optionText;
        this.choiceDestination = choiceDestination;
        this.traitScore = new HashMap<>();
        this.traitRequirement = new HashMap<>();
    }


}
