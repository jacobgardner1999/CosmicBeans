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
        assertEquals(game.getPlayer().getCurrentChoice().getChoiceText(), choiceText);
    }
}
