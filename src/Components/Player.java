package Components;

import java.util.HashMap;

public class Player {
    private Choice currentChoice;
    private HashMap<String, Integer> playerTraits = new HashMap<>();

    public Player() {
        this.playerTraits.put("Perception", 20);
    }

    public void setCurrentChoice(Choice choice) {
        currentChoice = choice;
    }

    public Choice getCurrentChoice() {
        return currentChoice;
    }

    public void makeChoice(int optionIndex) {
        Option option = currentChoice.optionsList.get(optionIndex);
        currentChoice = option.choiceDestination;
        updateTraits(option.traitScore);
    }

    private void updateTraits(HashMap<String, Integer> traitScore) {
        traitScore.forEach((key, value) -> playerTraits.put(key, playerTraits.get(key) + value));
    }

    public Integer getPlayerTraits(String trait) {
        return playerTraits.get(trait);
    }
}