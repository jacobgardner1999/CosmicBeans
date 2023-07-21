package Components;

import java.util.List;

public class Game {

    public void startGame(Player player) {
        OptionFactory factory = new OptionFactory();
        Choice choice1 = new Choice("You walk into town to get a coffee", null);
        Option option1 = factory.createEmptyOption("Walk into town for coffee", choice1);
        Choice startChoice = new Choice("Your alarm blares. 6:25", List.of(option1));
        giveChoice(player, startChoice);
    }

    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}