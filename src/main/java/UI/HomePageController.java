package UI;

import Components.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    private final Stage primaryStage;

    public HomePageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    public void initialize() {
    startButton.setOnAction(event -> {
        try {
            startGameHandler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    exitButton.setOnAction(event -> Platform.exit());
    }

    private void startGameHandler() throws IOException {
        Display display = new Display();
        display.viewGamePage(primaryStage);

    }
}
