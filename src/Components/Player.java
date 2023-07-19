package Components;

public class Player {
    private Choice currentChoice;

    public void setCurrentChoice(Choice choice) {
        currentChoice = choice;
    }

    public Choice getCurrentChoice() {
        return currentChoice;
    }
}
