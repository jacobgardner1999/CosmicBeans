package Tests;

import Components.Game;
import Components.Player;
import UI.Display;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class UITests extends ApplicationTest {
    private Display display;
    private Player player;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        display = new Display();
        Game game = new Game();
        player = new Player();

        game.setupGame(player);

        primaryStage.setTitle("Cosmic Beans DEV TEST");
        Platform.runLater(() -> {
            try {
                display.viewHomePage(primaryStage, player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @After
    public void tearDown() {
        Platform.runLater(() -> {
            try {
                display.viewHomePage(primaryStage, player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    @Test
    public void checkStartButton() {
        Button startButton = lookup("#startButton").query();

        Assertions.assertThat(startButton.getText()).isEqualTo("Start Game");
    }
    @Test
    public void checkStartGame() {
        clickOn("#startButton");

        WaitForAsyncUtils.waitForFxEvents();
        Text mainText = lookup("#mainText").query();
        Assertions.assertThat(mainText.getText()).isEqualTo("Your alarm blares. 6:25am.");
    }
    @Test
    public void playerChoiceUpdatesText() {
        clickOn("#startButton");
        clickOn("#choiceButton0_0");

        Text mainText = lookup("#mainText").query();
        Assertions.assertThat(mainText.getText()).isEqualTo("You get out of bed and start to get ready for work. Jumping in the shower, you reach to grab a body wash from the pot stuck to your wall with plastic suckers. The two shower gels you own are labelled 'Invigorate for Men', promising to energise you for the day, and 'Allure', promising to make you irresistible to the people around you. ");
    }
    @Test
    public void checkInsufficientTraitOptionDisabled() throws TimeoutException {
        clickOn("#startButton");
        WaitForAsyncUtils.waitForFxEvents();
        WaitForAsyncUtils.waitFor(5, TimeUnit.SECONDS, () -> {
            Button button = lookup("#choiceButton0_0").query();
            return button != null && button.isVisible();
        });
        clickOn("#choiceButton0_0");
        WaitForAsyncUtils.waitForFxEvents();
        WaitForAsyncUtils.waitFor(5, TimeUnit.SECONDS, () -> {
            Button button = lookup("#choiceButton1_1").query();
            return button != null && button.isVisible();
        });
        clickOn("#choiceButton2_1");

        Button disabledButton = lookup("#choiceButton1_1").query();

        Assertions.assertThat(disabledButton.isDisabled()).isTrue();
    }
}
