package com.example.finaltest;

public class SmallCharacter extends Character {
    @Override
    public void setRaceAndRole(String race, String role) {
        if (race.equals("faerie") || race.equals("halfling")) {
            this.race = race;
            if (role.equals("rogue") || role.equals("cleric") || role.equals("wizard")) {
                this.role = role;
            }
        }
    }
    // other methods and validation
}