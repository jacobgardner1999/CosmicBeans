package FXMLUI;

import Components.Game;
import Components.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game();
        Player player = new Player();

        game.setupGame(player);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout.fxml"));
        fxmlLoader.setControllerFactory(param -> new LayoutController(player));
        Parent root = fxmlLoader.load();

        LayoutController controller = fxmlLoader.getController();
        controller.updateText(player.getCurrentChoiceText());
        primaryStage.setTitle("Cosmic Beans DEV TEST");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}
