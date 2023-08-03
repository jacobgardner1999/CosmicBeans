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

        Choice choice1 = choiceFactory.createEndChoice("You walk into town to get a coffee.");
        Option option1 = optionFactory.createEmptyOption("Walk into town for coffee.", choice1);
        Choice choice2 = choiceFactory.createEndChoice("You sleep in, are late for work. You go to bed");
        Option option2 = optionFactory.createEmptyOption("Go back to sleep", choice2);
        return choiceFactory.createChoice("Your alarm blares. 6:25.", List.of(option1, option2));
    }


    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}