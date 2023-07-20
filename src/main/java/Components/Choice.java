package Components;

import java.util.List;

public class Choice {
    private final String choiceText;
    private List<Option> optionsList;

    public Choice(String choiceText, List<Option> optionsList) {
        this.choiceText = choiceText;
        this.optionsList = optionsList;
    }

    public Option getOption(int optionIndex) {
        return optionsList.get(optionIndex);
    }
    public String getChoiceText() {
        return choiceText;
    }
}