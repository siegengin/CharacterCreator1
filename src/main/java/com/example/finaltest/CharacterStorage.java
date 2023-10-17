package com.example.finaltest;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.*;
import java.util.*;

public class CharacterStorage {

    public void saveCharacterToFile(Character character) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("characters.txt", true)))) {
            out.println(character.name + "," + character.gender + "," + character.size + "," + character.race + "," + character.role);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save character!", ButtonType.OK);
            alert.show();
        }
    }

    public Character loadCharacterFromFile(String characterName) {
        try (BufferedReader br = new BufferedReader(new FileReader("characters.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(characterName)) {
                    Character loadedCharacter = createCharacterBySize(data[2]);
                    loadedCharacter.name = data[0];
                    loadedCharacter.gender = data[1];
                    loadedCharacter.size = data[2];
                    loadedCharacter.race = data[3];
                    loadedCharacter.role = data[4];
                    return loadedCharacter;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load character!", ButtonType.OK);
            alert.show();
        }
        return null;
    }

    public List<Character> getSavedCharacters() {
        List<Character> characters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("characters.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length != 5) {  // Assuming there are always 5 attributes for a character
                    System.out.println("Invalid line in characters.txt: " + line);
                    continue;
                }
                Character loadedCharacter = createCharacterBySize(data[2]);
                loadedCharacter.name = data[0];
                loadedCharacter.gender = data[1];
                loadedCharacter.size = data[2];
                loadedCharacter.race = data[3];
                loadedCharacter.role = data[4];

                characters.add(loadedCharacter);
                System.out.println("Loaded character: " + loadedCharacter.name);  // Log for debugging
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load characters!", ButtonType.OK);
            alert.show();
        }

        return characters;
    }


    private Character createCharacterBySize(String size) {
        switch (size) {
            case "Small":
                return new SmallCharacter();
            case "Medium":
                return new MediumCharacter();
            case "Large":
                return new LargeCharacter();
            default:
                throw new IllegalArgumentException("Unknown character size: " + size);
        }
    }
}
