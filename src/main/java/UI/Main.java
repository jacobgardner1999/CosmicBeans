package UI;
import Components.Game;
import Components.Option;
import Components.Player;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Player player = new Player();
        Game game = new Game();

        game.setupGame(player);

        Button button = new Button("GO!");

        Text mainText = new Text();
        StringProperty dynamicResultProperty = new SimpleStringProperty();
        mainText.textProperty().bind(dynamicResultProperty);

        Option placeholder = new Option("<CHOOSE AN OPTION>", null);
        ComboBox<Option> options = new ComboBox<>();
        options.getItems().add(placeholder);

        List<Option> choiceOptions = player.getCurrentChoice().getOptionsList();
        choiceOptions.forEach(option -> {
            options.getItems().add(option);
        });



        options.setValue(placeholder);

        options.setConverter(new StringConverter<>() {
            @Override
            public String toString(Option option) {
                return option == null ? null : option.getOptionText();
            }

            @Override
            public Option fromString(String s) {
                return null;
            }
        });

        button.setOnAction(event -> {
            int i = 0;
            int optionIndex = 404;
            for(Option option : player.getCurrentChoice().getOptionsList()) {
                if (option.equals(options.getValue())) {
                    optionIndex = i;
                }
                i += 1;
            }
            player.makeChoice(optionIndex);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainText, options, button);

        StackPane.setAlignment(stackPane, javafx.geometry.Pos.TOP_CENTER);

        StackPane.setMargin(mainText, new Insets(-40,0,0,0));
        StackPane.setMargin(options, new Insets(40,0,0,0));
        StackPane.setMargin(button, new Insets(120,0,0,0));

        Scene scene = new Scene(stackPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cosmic Beans Dev Test");
        primaryStage.show();

        new Thread(() -> {
            try {
                while (true) {
                    String dynamicResult = player.getCurrentChoiceText();

                    javafx.application.Platform.runLater(() -> dynamicResultProperty.set(dynamicResult));

                    Thread.sleep(1000); // Wait for 1 second (simulate result change)
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
