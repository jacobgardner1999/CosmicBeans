package Components;

import Helpers.InsufficientTraitException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private Choice currentChoice;
    private final Traits playerTraits;
    private final List<Choice> choiceMemory = new ArrayList<>();

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
        if(option.traitRequirement.isLessThan(playerTraits)) {
            choiceMemory.add(currentChoice);
            System.out.println(choiceMemory);
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
