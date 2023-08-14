import Components.*;
import Helpers.InsufficientTraitException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameLogicTests {

    @Test
    public void startGameInstance() {
        Game game = new Game();
        Player player = new Player();
        game.setupGame(player);

        String choiceText = "Your alarm blares. 6:25am.";
        Assertions.assertThat(player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void playerGivenNewChoiceOnMakeChoice() {
        Game game = new Game();
        Player player = new Player();
        game.setupGame(player);
        player.makeChoice(0);

        String choiceText = "You get out of bed and start to get ready for work. Jumping in the shower, you reach to grab a body wash from the pot stuck to your wall with plastic suckers. The two shower gels you own are labelled 'Invigorate for Men', promising to energise you for the day, and 'Allure', promising to make you irresistible to the people around you. ";
        Assertions.assertThat(player.getCurrentChoice().getChoiceText()).isEqualTo(choiceText);
    }

    @Test
    public void choiceUpdatesPlayerTrait() {
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
    public void optionNotAvailableBasedOnPlayerTrait() {
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
    public void playerMakesChoiceWithMultipleRequirements() {
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

    @Test
    public void saveGameState() {
        Game game = new Game();
        Player player = new Player();
        game.setupGame(player);

        player.makeChoice(0);
        player.makeChoice(1);

        SaveLoadManager.saveGame(player, "testSaveFile.dat");

        Player newPlayer = SaveLoadManager.loadGame("testSaveFile.dat");
        newPlayer.makeChoice(0);

        Assertions.assertThat(newPlayer.getCurrentChoiceText()).isEqualTo("You detour away from your normal walk to work onto a street you've never been on before. In fact... you're certain this road wasn't here last time you walked through this way. You definitely need a coffee. As you get to the door you notice a sign in the window. \"Serving now, hot bean juice with milk\". Quirky... you think to yourself.");
    }

    @Test
    public void updatePlayerMemory() {
        Game game = new Game();
        Player player = new Player();
        game.setupGame(player);

        player.makeChoice(0);
        List<String> expected = new ArrayList<>();
        expected.add("0_0");
        Assertions.assertThat(player.getChoiceMemory()).isEqualTo(expected);

        player.makeChoice(1);
        expected.add("1_0");
        player.makeChoice(0);
        expected.add("2_0");

        Assertions.assertThat(player.getChoiceMemory()).isEqualTo(expected);
    }
}
