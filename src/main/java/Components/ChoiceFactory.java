package Components;

import java.util.List;

public class ChoiceFactory {

    public Choice createChoice(String choiceText, List<Option> optionsList) {
        return new Choice(choiceText, optionsList);
    };

    public Choice createEndChoice(String choiceText) {
        return new Choice(choiceText, null);
    }
}
