package Tests;

import Components.*;
import Helpers.InsufficientTraitException;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTests {

    @Test
    public void StartGameInstance() {
        Game game = new Game();
        Player player = new Player();
        game.startGame(player);

        String choiceText = "Your alarm blares. 6:25";
        assertThat(player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void PlayerGivenNewChoiceOnMakeChoice() {
        Game game = new Game();
        Player player = new Player();
        game.startGame(player);
        player.makeChoice(0);

        String choiceText = "You walk into town to get a coffee";
        assertThat(player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void ChoiceUpdatesPlayerTrait() {
        Game game = new Game();
        Player player = new Player();
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();
        Traits optionTraits = new Traits(5, 10, 5, 0);
        Option option = factory.createOption("Option 1", null, optionTraits, new Traits());
        Choice choice = choiceFactory.createChoice("Choice", List.of(option));

        game.giveChoice(player, choice);
        player.makeChoice(0);
        assertThat(player.getPlayerTraits().getPerception()).isEqualTo(25);
        assertThat(player.getPlayerTraits().getHustle()).isEqualTo(30);
        assertThat(player.getPlayerTraits().getCharisma()).isEqualTo(25);
        assertThat(player.getPlayerTraits().getSnootiness()).isEqualTo(20);
    }

    @Test
    public void OptionNotAvailableBasedOnPlayerTrait() {
        Game game = new Game();
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();
        Player player = new Player();
        Traits traitRequirement = new Traits(50, 0, 0, 0);

        Choice badChoice = choiceFactory.createEndChoice("Un-choosable");

        Option option = factory.createOption("Option 1", badChoice, new Traits(), traitRequirement);
        Choice choice = choiceFactory.createChoice("Choice", List.of(option));

        game.giveChoice(player, choice);

        assertThatExceptionOfType(InsufficientTraitException.class)
                .isThrownBy(() -> player.makeChoice(0))
                .withMessage("Insufficient trait value.");
    }

    @Test
    public void PlayerMakesChoiceWithMultipleRequirements() {
        Game game = new Game();
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();
        Player player = new Player(new Traits(50, 45, 80, 30));
        Traits traitRequirement = new Traits(50, 45, 80, 30);

        Choice destination = choiceFactory.createEndChoice("Expected result");

        Option option = factory.createOption("Option 1", destination, new Traits(), traitRequirement);
        Choice choice = choiceFactory.createChoice("Second Choice", List.of(option));

        game.giveChoice(player, choice);
        player.makeChoice(0);

        assertThat(player.getCurrentChoiceText()).isEqualTo("Expected result");
    }

}
