package Components;

import java.util.List;

public class Game {

    public void setupGame(Player player) {
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();

        Choice choice1 = choiceFactory.createEndChoice("You walk into town to get a coffee.");
        Option option1 = factory.createEmptyOption("Walk into town for coffee.", choice1);
        Choice choice2 = choiceFactory.createEndChoice("You are lazy.");
        Option option2 = factory.createEmptyOption("Go back to sleep", choice2);
        Choice startChoice = choiceFactory.createChoice("Your alarm blares. 6:25.", List.of(option1, option2));
        giveChoice(player, startChoice);
    }


    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}