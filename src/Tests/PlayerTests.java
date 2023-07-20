package Tests;

import Components.Choice;
import Components.Game;
import Components.Option;
import Components.Traits;
import org.junit.jupiter.api.Test;
import java.util.List;

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

    @Test
    void ChoiceUpdatesPlayerTrait() {
        Game game = new Game();
        Traits optionTraits= new Traits(5);
        Option option = new Option("Option 1", null, optionTraits, null);
        Choice choice = new Choice("Choice", List.of(option));

        game.giveChoice(choice);
        game.player.makeChoice(0);
        assertEquals(25, game.player.getPerception());
    }

    @Test
    void OptionNotAvailableBasedOnPlayerTrait() {
        Game game = new Game();
        Traits traitRequirement= new Traits(50);

        Choice badChoice = new Choice("Un-choosable", null);
        Option option = new Option("Option 1", badChoice, null, traitRequirement);
        Choice choice = new Choice("Choice", List.of(option));

        game.giveChoice(choice);

        assertEquals("Choice", game.player.getCurrentChoice().getChoiceText());
    }
}
