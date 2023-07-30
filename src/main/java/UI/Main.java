package UI;
import Components.Option;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Text mainText = new Text("<CHOICE TEXT>");
        Button button = new Button("GO!");

        Option placeholder = new Option("<CHOOSE AN OPTION>", null);
        Option option1 = new Option("Option 1", null);
        Option option2 = new Option("Option 2", null);
        Option option3 = new Option("Option 3", null);

        ComboBox<Option> options = new ComboBox<>();
        options.getItems().addAll(placeholder, option1, option2, option3);
        options.setValue(placeholder);

        options.setConverter(new StringConverter<Option>() {
            @Override
            public String toString(Option option) {
                return option == null ? null : option.getOptionText();
            }

            @Override
            public Option fromString(String s) {
                return null;
            }
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
    }
}
