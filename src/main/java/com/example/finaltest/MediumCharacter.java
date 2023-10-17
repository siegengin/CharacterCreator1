package com.example.finaltest;

public class MediumCharacter extends Character {
    @Override
    public void setRaceAndRole(String race, String role) {
        if (race.equals("human") || race.equals("elf")) {
            this.race = race;
            if (role.equals("rogue") || role.equals("cleric") || role.equals("wizard") || role.equals("fighter")) {
                this.role = role;
            }
        }
    }
    // other methods and validation
}