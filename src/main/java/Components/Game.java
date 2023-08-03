package Components;

import java.util.List;

public class Game {

    public void setupGame(Player player) {
        Choice startChoice = generateChoices();

        giveChoice(player, startChoice);
    }

    private Choice generateChoices() {
        OptionFactory optionFactory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();

        Choice choice1 = choiceFactory.createChoice("You walk into town to get a coffee.");
        Choice choice2 = choiceFactory.createChoice("You sleep in, are late for work. You go to bed");
        Choice startChoice = choiceFactory.createChoice("Your alarm blares. 6:25.");

        Option option1 = optionFactory.createEmptyOption("Walk into town for coffee.", choice1);
        Option option2 = optionFactory.createEmptyOption("Go back to sleep", choice2);
        startChoice.addOptions(List.of(option1, option2));
        return startChoice;
    }


    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}