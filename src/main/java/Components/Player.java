package Components;

import Helpers.InsufficientTraitException;

public class Player {
    private Choice currentChoice;
    private final Traits playerTraits;

    public Player(Traits playerTraits) {
        this.playerTraits = playerTraits;
    }
    public Player() {
        this.playerTraits = new Traits(20, 20, 20, 20);
    }

    public void setCurrentChoice(Choice choice) {
        currentChoice = choice;
    }

    public Choice getCurrentChoice() {
        return currentChoice;
    }

    public String getCurrentChoiceText() { return currentChoice.getChoiceText(); }

    public void makeChoice(int optionIndex) {
        Option option = currentChoice.getOption(optionIndex);
        System.out.println("Making Choice!");
        if(option.traitRequirement.checkValid(playerTraits)) {
            currentChoice = option.choiceDestination;
            updateTraits(option.traitScore);
        }
        else
            throw new InsufficientTraitException("Insufficient trait value.");
    }

    public Traits getPlayerTraits() {
        return playerTraits;
    }

    private void updateTraits(Traits traitScore) {
        playerTraits.updateTraits(traitScore);
    }
}
