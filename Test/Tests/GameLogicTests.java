package Tests;

import Components.*;
import Helpers.InsufficientTraitException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GameLogicTests {

    @Test
    public void StartGameInstance() {
        Game game = new Game();
        Player player = new Player();
        game.setupGame(player);

        String choiceText = "Your alarm blares. 6:25am.";
        Assertions.assertThat(player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void PlayerGivenNewChoiceOnMakeChoice() {
        Game game = new Game();
        Player player = new Player();
        game.setupGame(player);
        player.makeChoice(0);

        String choiceText = "You get out of bed and start to get ready for work. Jumping in the shower, you reach to grab a body wash from the pot stuck to your wall with plastic suckers. The two shower gels you own are labelled 'Invigorate for Men', promising to energise you for the day, and 'Allure', promising to make you irresistible to the people around you. ";
        Assertions.assertThat(player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void ChoiceUpdatesPlayerTrait() {
        Game game = new Game();
        Player player = new Player();
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();
        Traits optionTraits = new Traits(5, 10, 5, 0);
        Option option = factory.createOption("Option 1", null, optionTraits, new Traits());
        Choice choice = choiceFactory.createChoice("Choice");
        choice.addOption(option);

        game.giveChoice(player, choice);
        player.makeChoice(0);
        Assertions.assertThat(player.getPlayerTraits().getPerception()).isEqualTo(25);
        Assertions.assertThat(player.getPlayerTraits().getHustle()).isEqualTo(30);
        Assertions.assertThat(player.getPlayerTraits().getCharisma()).isEqualTo(25);
        Assertions.assertThat(player.getPlayerTraits().getSnootiness()).isEqualTo(20);
    }

    @Test
    public void OptionNotAvailableBasedOnPlayerTrait() {
        Game game = new Game();
        OptionFactory factory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();
        Player player = new Player();
        Traits traitRequirement = new Traits(50, 0, 0, 0);

        Choice badChoice = choiceFactory.createChoice("Un-choosable");

        Option option = factory.createOption("Option 1", badChoice, new Traits(), traitRequirement);
        Choice choice = choiceFactory.createChoice("Choice");
        choice.addOption(option);

        game.giveChoice(player, choice);

        Assertions.assertThatExceptionOfType(InsufficientTraitException.class)
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

        Choice destination = choiceFactory.createChoice("Expected result");

        Option option = factory.createOption("Option 1", destination, new Traits(), traitRequirement);
        Choice choice = choiceFactory.createChoice("Second Choice");
        choice.addOption(option);

        game.giveChoice(player, choice);
        player.makeChoice(0);

        Assertions.assertThat(player.getCurrentChoiceText()).isEqualTo("Expected result");
    }

}
