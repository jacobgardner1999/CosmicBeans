package Components;

import java.util.List;

public class ChoiceFactory {

    public Choice createChoice(String choiceText) {
        return new Choice(choiceText);
    }
}