package Components;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public void startGame(Player player) {
        Choice choice1 = new Choice("You walk into town to get a coffee", null);
        Option option1 = new Option("Walk into town for coffee", choice1);
        List<Option> options = new ArrayList<>();
        options.add(option1);
        Choice startChoice = new Choice("Your alarm blares. 6:25", options);
        giveChoice(player, startChoice);
    }

    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}