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

public class UITests extends ApplicationTest {
    private Display display;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        display = new Display();

        primaryStage.setTitle("Cosmic Beans DEV TEST");
        Platform.runLater(() -> {
            try {
                display.viewHomePage(primaryStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @After
    public void tearDown() {
        Platform.runLater(() -> {
            try {
                display.viewHomePage(primaryStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void checkStartButton() {
        Button startButton = lookup("#startButton").query();

        Assertions.assertThat(startButton.getText()).isEqualTo("New Game");
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

        sleep(500);
        Text mainText = lookup("#mainText").query();
        Assertions.assertThat(mainText.getText()).isEqualTo("You get out of bed and start to get ready for work. Jumping in the shower, you reach to grab a body wash from the pot stuck to your wall with plastic suckers. The two shower gels you own are labelled 'Invigorate for Men', promising to energise you for the day, and 'Allure', promising to make you irresistible to the people around you. ");
    }
    @Test
    public void checkInsufficientTraitOptionDisabled() {
        clickOn("#startButton");
        sleep(100);
        clickOn("#choiceButton0_0");
        sleep(500);
        clickOn("#choiceButton1_1");
        sleep(500);
        Button disabledButton = lookup("#choiceButton2_1").query();

        Assertions.assertThat(disabledButton.isDisabled()).isTrue();
    }
    @Test
    public void checkRequiredTraitOptionEnabled() {
        clickOn("#startButton");
        sleep(100);
        clickOn("#choiceButton0_0");
        sleep(500);
        clickOn("#choiceButton1_0");
        sleep(500);
        Button enabledButton = lookup("#choiceButton2_1").query();

        Assertions.assertThat(enabledButton.isDisabled()).isFalse();
    }
    @Test
    public void checkTraitUpdates() {
        clickOn("#startButton");
        sleep(100);
        clickOn("#choiceButton0_0");
        sleep(500);
        Text hustle = lookup("#hustleDisplay").query();

        Assertions.assertThat(Integer.valueOf(hustle.getText())).isEqualTo(20);

        clickOn("#choiceButton1_0");
        sleep(500);
        Assertions.assertThat(Integer.valueOf(hustle.getText())).isEqualTo(30);
    }
}
