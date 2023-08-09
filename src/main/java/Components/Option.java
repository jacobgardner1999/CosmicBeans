package Components;

import java.io.Serializable;

public class Option implements Serializable {
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
        this.traitScore = new Traits();
        this.traitRequirement = new Traits();
    }

    public String getOptionText() {
        return optionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return optionText.equals(option.optionText);
    }
}