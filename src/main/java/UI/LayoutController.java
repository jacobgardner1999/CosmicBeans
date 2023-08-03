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

    private String dynamicResult = "";
    private Timeline timeline;
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
            dynamicResult = newText;
            startTypingAnimation();
            generateButtons();
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

    private void startTypingAnimation() {
        if (timeline != null && timeline.getStatus() != Animation.Status.STOPPED || dynamicResultProperty.get().equals(dynamicResult)) {
            return;
        }

        dynamicResultProperty.set("");

        timeline = new Timeline();
        Duration frameDuration = Duration.millis(100);

        for (int i = 0; i <= dynamicResult.length(); i++) {
            int finalI = i;
            KeyFrame keyFrame = new KeyFrame(frameDuration.multiply(i), event -> dynamicResultProperty.set(dynamicResult.substring(0, finalI)));
            timeline.getKeyFrames().add(keyFrame);
        }

        KeyFrame lastKeyFrame = new KeyFrame(frameDuration.multiply(dynamicResult.length() + 1), event -> {
            dynamicResultProperty.set(dynamicResult);
            timeline = null;
        });
        timeline.getKeyFrames().add(lastKeyFrame);

        timeline.setCycleCount(1);
        timeline.play();
    }
}
