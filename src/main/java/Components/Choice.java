package Components;

import java.util.ArrayList;
import java.util.List;

public class Choice {
    private final String choiceText;
    private final List<Option> optionsList;

    public Choice(String choiceText) {
        this.choiceText = choiceText;
        this.optionsList = new ArrayList<>();
    }
    public List<Option> getOptionsList() {
        return optionsList;
    }
    public Option getOption(int optionIndex) {
        return optionsList.get(optionIndex);
    }
    public String getChoiceText() {
        return choiceText;
    }

    public void addOption(Option option) {
        optionsList.add(option);
    }
    public void addOptions(List<Option> options) {
        optionsList.addAll(options);
    }
}