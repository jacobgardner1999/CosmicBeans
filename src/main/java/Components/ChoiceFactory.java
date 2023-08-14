package Components;

public class ChoiceFactory {

    public Choice createChoice(String choiceText, String choiceId) {
        return new Choice(choiceText, choiceId);
    }

    public Choice createChoice(String choiceText) {return new Choice(choiceText, "test"); }
}