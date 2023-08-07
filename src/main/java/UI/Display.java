package UI;

import Components.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Display {
    public void viewHomePage(Stage primaryStage, Player player) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
        fxmlLoader.setControllerFactory(param -> new HomePageController(player, primaryStage));
        Parent root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void viewGamePage(Stage primaryStage, Player player) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gamePage.fxml"));
        Parent root = fxmlLoader.load();

        GamePageController controller = fxmlLoader.getController();
        controller.setPlayer(player);
        controller.setUpGameScreen();

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        new Thread(() -> {
            try {
                while (true) {
                    String newText = player.getCurrentChoiceText();

                    controller.updateText(newText);

                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
