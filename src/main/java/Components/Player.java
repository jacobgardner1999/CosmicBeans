package Components;

public class Player {
    private Choice currentChoice;
    private final Traits playerTraits;

    public Player() {
        this.playerTraits = new Traits(20);
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

    private void updateTraits(Traits traitScore) {
        playerTraits.updateTraits(traitScore);
    }

    public Integer getPerception() {
        return playerTraits.getPerception();
    }
}
