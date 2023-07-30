package Components;

import java.util.List;

public class Game {

    public void startGame(Player player) {
        Choice startChoice = generateChoices();
        giveChoice(player, startChoice);
    }

    private Choice generateChoices() {
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();

        Choice choice1 = choiceFactory.createEndChoice("You walk into town to get a coffee.");
        Option option1 = factory.createEmptyOption("Walk into town for coffee.", choice1);

        return choiceFactory.createChoice("Your alarm blares. 6:25.", List.of(option1));
    }

    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}