package UI;
import Components.Game;
import Components.Option;
import Components.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Objects;


public class Main extends Application{
    private final StringProperty dynamicResultProperty = new SimpleStringProperty();
    private String dynamicResult = "";
    private Timeline timeline;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Player player = new Player();
        Game game = new Game();

        game.setupGame(player);

        Text mainText = new Text();
        mainText.textProperty().bind(dynamicResultProperty);
        dynamicResultProperty.set(dynamicResult);
        mainText.setFill(Color.WHITE);

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

        stackPane.setStyle("-fx-background-color: black;");

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
                    String newText = player.getCurrentChoiceText();

                    updateTextFromThread(newText);
                    javafx.application.Platform.runLater(generateButtons);

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateTextFromThread(String newText) {
        javafx.application.Platform.runLater(() -> {
            dynamicResult = newText;
            startTypingAnimation();
        });
    }
    private void startTypingAnimation() {
        if (timeline != null && timeline.getStatus() != Animation.Status.STOPPED || dynamicResultProperty.get().equals(dynamicResult)) {
            return;
        }

        timeline = new Timeline();

        Duration frameDuration = Duration.millis(100);

        for (int i = 0; i <= dynamicResult.length(); i++) {
            int finalI = i;
            KeyFrame keyFrame = new KeyFrame(frameDuration.multiply(i), event -> {
                dynamicResultProperty.set(dynamicResult.substring(0, finalI));
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        KeyFrame lastKeyFrame = new KeyFrame(frameDuration.multiply(dynamicResult.length() + 1), event -> {
            dynamicResultProperty.set(dynamicResult);
        });
        timeline.getKeyFrames().add(lastKeyFrame);

        timeline.setCycleCount(1);

        timeline.play();
    }
}
