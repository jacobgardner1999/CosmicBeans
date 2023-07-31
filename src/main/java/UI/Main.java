package UI;
import Components.Game;
import Components.Option;
import Components.Player;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Player player = new Player();
        Game game = new Game();

        game.setupGame(player);

        Text mainText = new Text();
        StringProperty dynamicResultProperty = new SimpleStringProperty();
        mainText.textProperty().bind(dynamicResultProperty);

        VBox buttonBox = new VBox();
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.setSpacing(10);

        Runnable generateButtons = () -> {
            buttonBox.getChildren().clear();
            for (Option option : player.getCurrentChoice().getOptionsList()) {
                Button button = new Button(option.getOptionText());
                buttonBox.getChildren().add(button);

                button.setOnAction(event -> {
                    int i = 0;
                    int optionIndex = 404;
                    for(Option o : player.getCurrentChoice().getOptionsList()) {
                        if (Objects.equals(o.getOptionText(), button.getText())) {
                            optionIndex = i;
                        }
                        i += 1;
                    }
                    player.makeChoice(optionIndex);
                });
            }
        };

        generateButtons.run();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainText, buttonBox);

        StackPane.setAlignment(stackPane, javafx.geometry.Pos.TOP_CENTER);

        StackPane.setMargin(mainText, new Insets(-40,0,0,0));
        StackPane.setMargin(buttonBox, new Insets(40, 0, 0, 0));

        Scene scene = new Scene(stackPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cosmic Beans Dev Test");
        primaryStage.show();

        new Thread(() -> {
            try {
                while (true) {
                    String dynamicResult = player.getCurrentChoiceText();

                    javafx.application.Platform.runLater(() -> dynamicResultProperty.set(dynamicResult));
                    javafx.application.Platform.runLater(generateButtons);

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
