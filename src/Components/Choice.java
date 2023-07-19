package Components;

import java.util.List;

public class Choice {
    private String choiceText;
    private List<Option> optionsList;

    public Choice(String choiceText, List<Option> optionsList) {
        this.choiceText = choiceText;
        this.optionsList = optionsList;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public List<Option> getOptionsList() {
        return optionsList;
    }
}
