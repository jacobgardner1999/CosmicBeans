package Tests;

import Components.Choice;
import Components.Game;
import Components.Option;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void ChoiceUpdatesPlayerTrait() {
        Game game = new Game();
        HashMap<String, Integer> optionTraits= new HashMap<>();
        optionTraits.put("Perception", 5);
        Option option = new Option("Option 1", null, optionTraits);
        Choice choice = new Choice("Choice", List.of(option));

        game.giveChoice(choice);
        game.player.makeChoice(0);
        assertEquals(25, game.player.getPlayerTraits("Perception"));
    }

    @Test
    void OptionNotAvailableBasedOnPlayerTrait() {
        Game game = new Game();
        HashMap<String, Integer> traitRequirement= new HashMap<>();
        traitRequirement.put("Perception", 5);
        Option option = new Option("Option 1", null, null, traitRequirement);
        Choice choice = new Choice("Choice", List.of(option));

        game.giveChoice(choice);

        assertThrows(InsufficientTraitException.class, () -> {
            game.player.makeChoice(0);
        });
    }
}
