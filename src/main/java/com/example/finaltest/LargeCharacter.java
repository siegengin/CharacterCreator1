package com.example.finaltest;

public class LargeCharacter extends Character {
    @Override
    public void setRaceAndRole(String race, String role) {
        if (race.equals("orc") || race.equals("ogre")) {
            this.race = race;
            if (role.equals("fighter") || role.equals("cleric") || role.equals("wizard")) {
                this.role = role;
            }
        }
    }
    // other methods and validation
}