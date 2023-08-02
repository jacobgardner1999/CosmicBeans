package FXMLUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import Components.Player;

public class LayoutController {
    private final StringProperty dynamicResultProperty = new SimpleStringProperty("Default Text");
    private String dynamicResult = "";
    private Timeline timeline;
    private final Player player;
    private String previousText;

    public LayoutController(Player player) {
        this.player = player;
        previousText = player.getCurrentChoiceText();
        Thread changeDetectionThread = new Thread(() -> {
            String currentText = player.getCurrentChoiceText();
            if (!currentText.equals(previousText)) {
                updateText(currentText);
                previousText = currentText;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        changeDetectionThread.setDaemon(true);
        changeDetectionThread.start();
    }

    @FXML
    private Text mainText;
    @FXML
    private VBox buttonContainer;

    @FXML
    private void initialize() {
        mainText.textProperty().bind(dynamicResultProperty);
    }

    public void updateText(String newText) {
        javafx.application.Platform.runLater(() -> {
            dynamicResult = newText;
            startTypingAnimation();
        });
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
