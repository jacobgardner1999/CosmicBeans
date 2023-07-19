package Components;

import java.util.HashMap;
public class Option {
    private final String optionText;
    public Choice choiceDestination;
    public HashMap<String, Integer> traitScore;
    public Option(String optionText, Choice choiceDestination, HashMap<String, Integer> traitScore) {
        this.optionText = optionText;
        this.choiceDestination = choiceDestination;
        this.traitScore = traitScore;
    }

}
