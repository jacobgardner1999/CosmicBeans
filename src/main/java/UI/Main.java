package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Display display = new Display();

        primaryStage.setTitle("Cosmic Beans DEV TEST");
        display.viewHomePage(primaryStage);
    }
}
