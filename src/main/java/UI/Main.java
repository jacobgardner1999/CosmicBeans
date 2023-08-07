package UI;

import Components.Game;
import Components.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Display display = new Display();
        Game game = new Game();
        Player player = new Player();

        game.setupGame(player);

        primaryStage.setTitle("Cosmic Beans DEV TEST");
        display.viewHomePage(primaryStage, player);
    }
}
