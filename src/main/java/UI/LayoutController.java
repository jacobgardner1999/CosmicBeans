package UI;

import Components.Option;
import Components.Traits;
import javafx.animation.FillTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import Components.Player;

import java.util.Objects;

public class LayoutController {
    private final StringProperty dynamicResultProperty = new SimpleStringProperty("Default Text");
    private final StringProperty perceptionProperty = new SimpleStringProperty("@@@");
    private final StringProperty hustleProperty = new SimpleStringProperty("@@@");
    private final StringProperty charismaProperty = new SimpleStringProperty("@@@");
    private final StringProperty snootinessProperty = new SimpleStringProperty("@@@");

    private final Player player;
    @FXML
    private StackPane loadingOverlay;


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
        perceptionDisplay.textProperty().bind(perceptionProperty);
        perceptionProperty.addListener((observable, oldValue, newValue) -> {
            perceptionDisplay.setFill(Color.GREEN);
            updateAnimation(perceptionDisplay);
        });
        hustleDisplay.textProperty().bind(hustleProperty);
        hustleProperty.addListener((observable, oldValue, newValue) -> {
            hustleDisplay.setFill(Color.GREEN);
            updateAnimation(hustleDisplay);
        });
        charismaDisplay.textProperty().bind(charismaProperty);
        charismaProperty.addListener((observable, oldValue, newValue) -> {
            charismaDisplay.setFill(Color.GREEN);
            updateAnimation(charismaDisplay);
        });
        snootinessDisplay.textProperty().bind(snootinessProperty);
        snootinessProperty.addListener((observable, oldValue, newValue) -> {
            snootinessDisplay.setFill(Color.GREEN);
            updateAnimation(snootinessDisplay);
        });
        updateText(player.getCurrentChoiceText());
        generateButtons();
    }

    public void updateText(String newText) {
        javafx.application.Platform.runLater(() -> {
            if(!(Objects.equals(dynamicResultProperty.get(), newText))) {
                dynamicResultProperty.set(newText);
                updateTraits();
                generateButtons();
                loadingOverlay.setVisible(false);
            }
        });
    }

    private void generateButtons() {
        buttonContainer.getChildren().clear();
        for (Option option : player.getCurrentChoice().getOptionsList()) {
            Button button = new Button(option.getOptionText());
            button.getStyleClass().add("button");
            buttonContainer.getChildren().add(button);
            if(!(option.traitRequirement.isLessThan(player.getPlayerTraits()))) {
                button.setDisable(true);
            }

            button.setOnAction(event -> {
                loadingOverlay.setVisible(true);
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
    private void updateTraits() {
        Traits traits = player.getPlayerTraits();

        perceptionProperty.set(String.valueOf(traits.getPerception()));
        hustleProperty.set(String.valueOf(traits.getHustle()));
        charismaProperty.set(String.valueOf(traits.getCharisma()));
        snootinessProperty.set(String.valueOf(traits.getSnootiness()));
    }

    private void updateAnimation(Text textNode) {
        Color startColor = Color.GREEN;
        Color endColor = Color.WHITE;
        Duration duration = Duration.seconds(3);

        FillTransition fillTransition = new FillTransition(duration, textNode, startColor, endColor);
        fillTransition.setOnFinished(event -> textNode.setFill(endColor));
        fillTransition.play();
    }
}

