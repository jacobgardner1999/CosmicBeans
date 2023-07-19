package Components;

public class Player {
    private Choice currentChoice;

    public void setCurrentChoice(Choice choice) {
        currentChoice = choice;
    }

    public Choice getCurrentChoice() {
        return currentChoice;
    }

    public void makeChoice(int optionIndex) {
        currentChoice = currentChoice.optionsList.get(optionIndex).choiceDestination;
    }
}
