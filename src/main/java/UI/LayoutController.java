package UI;

import Components.Option;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import Components.Player;

import java.util.Objects;

public class LayoutController {
    private final StringProperty dynamicResultProperty = new SimpleStringProperty("Default Text");

    private final Player player;


    public LayoutController(Player player) {
        this.player = player;
    }

    @FXML
    private Text mainText;
    @FXML
    private VBox buttonContainer;
    @FXML
    private Text perceptionDisplay;
    @FXML
    private Text hustleDisplay;
    @FXML
    private Text charismaDisplay;
    @FXML
    private Text snootinessDisplay;
    @FXML
    private void initialize() {
        mainText.textProperty().bind(dynamicResultProperty);
        updateText(player.getCurrentChoiceText());
        generateButtons();
    }

    public void updateText(String newText) {
        javafx.application.Platform.runLater(() -> {
            if(!(Objects.equals(dynamicResultProperty.get(), newText))) {
                dynamicResultProperty.set(newText);
                generateButtons();
            }
        });
    }

    private void generateButtons() {
        buttonContainer.getChildren().clear();
        for (Option option : player.getCurrentChoice().getOptionsList()) {
            Button button = new Button(option.getOptionText());
            buttonContainer.getChildren().add(button);

            button.setOnAction(event -> {
                int i = 0;
                int optionIndex = 404;
                for (Option o : player.getCurrentChoice().getOptionsList()) {
                    if (Objects.equals(o.getOptionText(), button.getText())) {
                        optionIndex = i;
                    }
                    i += 1;
                }
                player.makeChoice(optionIndex);
            });
        }
    }
}
