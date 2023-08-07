package UI;

import Components.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    private final Player player;
    private final Stage primaryStage;

    public HomePageController(Player player, Stage primaryStage) {
        this.player = player;
        this.primaryStage = primaryStage;
    }

    @FXML
    Button startButton;
    @FXML
    public void initialize() {
    startButton.setOnAction(event -> {
        try {
            startGameHandler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    }

    private void startGameHandler() throws IOException {
        Display display = new Display();
        display.viewGamePage(primaryStage, player);

    }
}
