package com.example.finaltest;

public class GameCharacter extends Character {

    @Override
    public void setRaceAndRole(String race, String role) {
        this.race = race;
        this.role = role;
    }

    // Implement other missing setters here
    public void setName(String name) {
        this.name = name;
    }

}
