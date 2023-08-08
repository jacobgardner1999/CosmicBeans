package Tests;

import Components.Game;
import Components.Player;
import UI.Display;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;

public class UITests extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Display display = new Display();
        Game game = new Game();
        Player player = new Player();

        game.setupGame(player);

        primaryStage.setTitle("Cosmic Beans DEV TEST");
        display.viewHomePage(primaryStage, player);
    }

    @Test
    public void checkStartButton() {
        Button startButton = lookup("#startButton").query();

        Assertions.assertThat(startButton.getText()).isEqualTo("Start Game");
    }
}
