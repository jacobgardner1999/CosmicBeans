package UI;

import Components.*;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class GamePageController {
    private final StringProperty dynamicResultProperty = new SimpleStringProperty("");
    private final StringProperty perceptionProperty = new SimpleStringProperty("20");
    private final StringProperty hustleProperty = new SimpleStringProperty("20");
    private final StringProperty charismaProperty = new SimpleStringProperty("20");
    private final StringProperty snootinessProperty = new SimpleStringProperty("20");

    private final Display display = new Display();
    private Stage primaryStage;
    private Player player;
    private final int[] index = new int[] {0, 0};


    public GamePageController() {
    }
    @FXML
    private StackPane loadingOverlay;
    @FXML
    private StackPane savingOverlay;
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
    private Button homeButton;
    @FXML
    private Button saveButton;
    @FXML
    private void initialize() {
        mainText.textProperty().bind(dynamicResultProperty);
        perceptionDisplay.textProperty().bind(perceptionProperty);
        perceptionDisplay.setFill(Color.WHITE);
        perceptionProperty.addListener((observable, oldValue, newValue) -> {
            perceptionDisplay.setFill(Color.GREEN);
            if(Integer.parseInt(oldValue) < Integer.parseInt(newValue)) { updateAnimation(perceptionDisplay, Color.GREEN);}
            else { updateAnimation(perceptionDisplay, Color.RED);}
        });
        hustleDisplay.textProperty().bind(hustleProperty);
        hustleDisplay.setFill(Color.WHITE);
        hustleProperty.addListener((observable, oldValue, newValue) -> {
            hustleDisplay.setFill(Color.GREEN);
            if(Integer.parseInt(oldValue) < Integer.parseInt(newValue)) { updateAnimation(hustleDisplay, Color.GREEN);}
            else { updateAnimation(hustleDisplay, Color.RED);}
        });
        charismaDisplay.textProperty().bind(charismaProperty);
        charismaDisplay.setFill(Color.WHITE);
        charismaProperty.addListener((observable, oldValue, newValue) -> {
            charismaDisplay.setFill(Color.GREEN);
            if(Integer.parseInt(oldValue) < Integer.parseInt(newValue)) { updateAnimation(charismaDisplay, Color.GREEN);}
            else { updateAnimation(charismaDisplay, Color.RED);}
        });
        snootinessDisplay.textProperty().bind(snootinessProperty);
        charismaDisplay.setFill(Color.WHITE);
        snootinessProperty.addListener((observable, oldValue, newValue) -> {
            snootinessDisplay.setFill(Color.GREEN);
            if(Integer.parseInt(oldValue) < Integer.parseInt(newValue)) { updateAnimation(snootinessDisplay, Color.GREEN);}
            else { updateAnimation(snootinessDisplay, Color.RED);}
        });
        homeButton.setOnAction(event -> {
            try {
                display.viewHomePage(primaryStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        saveButton.setOnAction(event -> {
            savingOverlay.setVisible(true);
            System.out.println("Saving...");

            new Thread(() -> {
                SaveLoadManager.saveGame(player, "saveFile.dat");

                ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                executor.schedule(() -> {
                    Platform.runLater(() -> {
                        System.out.println("Save completed!");
                        savingOverlay.setVisible(false);
                    });
                    executor.shutdown();
                }, 1000, TimeUnit.MILLISECONDS);
            }).start();
        });
    }

    public void setUpGameScreen() {
        this.player = new Player();
        Game game = new Game();

        game.setupGame(player);
        new Thread(() -> {
            try {
                while (true) {
                    String newText = player.getCurrentChoiceText();

                    updateText(newText);

                    sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setUpGameScreen(Player player) {
        this.player = player;
        new Thread(() -> {
            try {
                while (true) {
                    String newText = player.getCurrentChoiceText();

                    updateText(newText);

                    sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
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
        index[1] = 0;
        for (Option option : player.getCurrentChoice().getOptionsList()) {
            Button button = new Button(option.getOptionText());
            button.getStyleClass().add("button");
            button.setId("choiceButton" + index[0] + "_" + index[1]);
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
                if(optionIndex != 404) {
                    player.makeChoice(optionIndex);
                }
                else {
                    System.out.println("Button option index out of bounds.");
                }
            });
            index[1]++;
        }
        index[0]++;
    }
    private void updateTraits() {
        Traits traits = player.getPlayerTraits();

        perceptionProperty.set(String.valueOf(traits.getPerception()));
        hustleProperty.set(String.valueOf(traits.getHustle()));
        charismaProperty.set(String.valueOf(traits.getCharisma()));
        snootinessProperty.set(String.valueOf(traits.getSnootiness()));
    }

    private void updateAnimation(Text textNode, Color startColor) {
        Color endColor = Color.WHITE;
        Duration duration = Duration.seconds(3);

        FillTransition fillTransition = new FillTransition(duration, textNode, startColor, endColor);
        fillTransition.setOnFinished(event -> textNode.setFill(endColor));
        fillTransition.play();
    }

    public void setStage(Stage primaryStage) { this.primaryStage = primaryStage; }
}

