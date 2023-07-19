package Tests;

import Components.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {

    @Test
    void StartGameInstance() {
        Game game = new Game();
        game.startGame();

        String choiceText = "Your alarm blares. 6:25";
        assertEquals(choiceText, game.player.getCurrentChoice().getChoiceText());
    }

    @Test
    void PlayerGivenNewChoiceOnMakeChoice() {
        Game game = new Game();
        game.startGame();
        game.player.makeChoice(0);

        String choiceText = "You walk into town to get a coffee";
        assertEquals(choiceText, game.player.getCurrentChoice().getChoiceText());
    }
}
