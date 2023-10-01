import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CharacterCreator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Basic UI setup
        VBox root = new VBox();
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");

        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("Small", "Medium", "Large");

        ComboBox<String> raceBox = new ComboBox<>();

        ComboBox<String> roleBox = new ComboBox<>();

        sizeBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            raceBox.getItems().clear();
            roleBox.getItems().clear();
            if (newVal.equals("Small")) {
                raceBox.getItems().addAll("faerie", "halfling");
            } else if (newVal.equals("Medium")) {
                raceBox.getItems().addAll("human", "elf");
            } else {
                raceBox.getItems().addAll("orc", "ogre");
            }
        });

        raceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            roleBox.getItems().clear();
            if (newVal.equals("faerie") || newVal.equals("halfling")) {
                roleBox.getItems().addAll("rogue", "cleric", "wizard");
            } else if (newVal.equals("human") || newVal.equals("elf")) {
                roleBox.getItems().addAll("rogue", "fighter", "cleric", "wizard");
            } else {
                roleBox.getItems().addAll("fighter", "cleric", "wizard");
            }
        });

        Button createButton = new Button("Create Character");
        createButton.setOnAction(e -> {
            // Basic validation and creation logic here
            // This will also involve creating the right Character object
        });

        root.getChildren().addAll(nameField, genderBox, sizeBox, raceBox, roleBox, createButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("D&D Character Creator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
