package Components;

public class Game {
    private final Player player;

    public Game() {
        this.player = new Player();
    }

    public void startGame() {
        Choice choice = new Choice("Your alarm blares. 6:25", null);
        giveChoice(choice);
    }

    private void giveChoice(Choice choice) {
        player.setCurrentChoice(choice);
    }

    public Player getPlayer() {
        return player;
    }
}