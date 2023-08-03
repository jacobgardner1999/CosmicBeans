package Components;

public class OptionFactory {

    public Option createOption(String optionText, Choice choiceDestination, Traits traitScore, Traits traitRequirement) {
        return new Option(optionText, choiceDestination, traitScore, traitRequirement);
    }

    public Option createOption(String optionText, Choice choiceDestination) {
        return new Option(optionText, choiceDestination);
    }
}
