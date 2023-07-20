package Tests;

import Components.Choice;
import Components.Game;
import Components.Option;
import Components.Traits;
import Helpers.InsufficientTraitException;
import org.junit.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTests {

    @Test
    public void StartGameInstance() {
        Game game = new Game();
        game.startGame();

        String choiceText = "Your alarm blares. 6:25";
        assertThat(game.player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void PlayerGivenNewChoiceOnMakeChoice() {
        Game game = new Game();
        game.startGame();
        game.player.makeChoice(0);

        String choiceText = "You walk into town to get a coffee";
        assertThat(game.player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void ChoiceUpdatesPlayerTrait() {
        Game game = new Game();
        Traits optionTraits= new Traits(5);
        Option option = new Option("Option 1", null, optionTraits, new Traits());
        Choice choice = new Choice("Choice", List.of(option));

        game.giveChoice(choice);
        game.player.makeChoice(0);
        assertThat(game.player.getPerception()).isEqualTo(25);
    }

    @Test
    public void OptionNotAvailableBasedOnPlayerTrait() {
        Game game = new Game();
        Traits traitRequirement= new Traits(50);

        Choice badChoice = new Choice("Un-choosable", null);

        Option option = new Option("Option 1", badChoice, new Traits(), traitRequirement);
        Choice choice = new Choice("Choice", List.of(option));

        game.giveChoice(choice);

        assertThatExceptionOfType(InsufficientTraitException.class)
                .isThrownBy(() -> game.player.makeChoice(0))
                .withMessage("Insufficient trait value.");
    }
}
