package Components;

public class ChoiceFactory {

    public Choice createChoice(String choiceText) {
        return new Choice(choiceText);
    }
}