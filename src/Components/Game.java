package Components;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final Player player;

    public Game() {
        this.player = new Player();
    }

    public void startGame() {
        Choice choice1 = new Choice("You walk into town to get a coffee", null);
        Option option1 = new Option("Walk into town for coffee", choice1);
        List<Option> options = new ArrayList<>();
        options.add(option1);
        Choice startChoice = new Choice("Your alarm blares. 6:25", options);
        giveChoice(startChoice);
    }

    private void giveChoice(Choice choice) {
        player.setCurrentChoice(choice);
    }

}