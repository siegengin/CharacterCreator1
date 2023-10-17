package com.example.finaltest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.List;

public class CharacterCreator extends Application {

    private final CharacterStorage storage = new CharacterStorage();
    private final ListView<String> characterListView = new ListView<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Font font = Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 14);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("1", "2", "3");

        Label sizeLabel = new Label("Size:");
        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("Small", "Medium", "Large");

        Label raceLabel = new Label("Race:");
        ComboBox<String> raceBox = new ComboBox<>();

        Label roleLabel = new Label("Role:");
        ComboBox<String> roleBox = new ComboBox<>();

        sizeBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            raceBox.getItems().clear();
            roleBox.getItems().clear();
            if (newVal == null) return;  // Exit if newVal is null

            switch (newVal) {
                case "Small":
                    raceBox.getItems().addAll("faerie", "halfling");
                    break;
                case "Medium":
                    raceBox.getItems().addAll("human", "elf");
                    break;
                default:
                    raceBox.getItems().addAll("orc", "ogre");
                    break;
            }
        });

        raceBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            roleBox.getItems().clear();
            if (newVal != null) {
                switch (newVal) {
                    case "faerie":
                    case "halfling":
                        roleBox.getItems().addAll("rogue", "cleric", "wizard");
                        break;
                    case "human":
                    case "elf":
                        roleBox.getItems().addAll("rogue", "fighter", "cleric", "wizard");
                        break;
                    case "orc":
                    case "ogre":
                        roleBox.getItems().addAll("fighter", "cleric", "wizard");
                        break;
                }
            }
        });

        Button createButton = new Button("Create Character");
        String stylesheet = getClass().getResource("/styles.css").toExternalForm();
        createButton.setPrefSize(250, 50);
        createButton.setOnAction(e -> {
            if (nameField.getText().isEmpty() || genderBox.getValue() == null || sizeBox.getValue() == null
                    || raceBox.getValue() == null || roleBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled out!", ButtonType.OK);
                alert.getDialogPane().getStylesheets().add(stylesheet);
                alert.show();

            } else {
                // Create the character and save it
                GameCharacter character = new GameCharacter();
                character.setName(nameField.getText());
                character.setGender(genderBox.getValue());
                character.setSize(sizeBox.getValue());
                character.setRaceAndRole(raceBox.getValue(), roleBox.getValue());

                storage.saveCharacterToFile(character);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Character created successfully!", ButtonType.OK);
                alert.getDialogPane().getStylesheets().add(stylesheet);
                alert.show();
            }
        });


        Button clearButton = new Button("Clear Fields");
        clearButton.setPrefSize(250, 50);
        clearButton.setOnAction(e -> {
            nameField.clear();
            genderBox.getSelectionModel().clearSelection();
            sizeBox.getSelectionModel().clearSelection();
            raceBox.getItems().clear();
            roleBox.getItems().clear();
        });

        Label listLabel = new Label("Created Characters:");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(genderLabel, 0, 1);
        grid.add(genderBox, 1, 1);
        grid.add(sizeLabel, 0, 2);
        grid.add(sizeBox, 1, 2);
        grid.add(raceLabel, 0, 3);
        grid.add(raceBox, 1, 3);
        grid.add(roleLabel, 0, 4);
        grid.add(roleBox, 1, 4);
        grid.add(createButton, 0, 5);
        grid.add(clearButton, 1, 5);
        grid.add(listLabel, 0, 6);
        grid.add(characterListView, 0, 7, 2, 1); // Spanning 2 columns

        Scene scene = new Scene(grid, 600, 800);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Character Creator");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateCharacterListView();
    }

    private void updateCharacterListView() {
        characterListView.getItems().clear();
        List<Character> characters = storage.getSavedCharacters();
        for (Character character : characters) {
            characterListView.getItems().add(character.getName());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
