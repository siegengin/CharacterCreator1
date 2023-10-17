package com.example.finaltest;

public abstract class Character {
    protected String name;
    protected String gender;
    protected String size;
    protected String race;
    protected String role;

    public abstract void setRaceAndRole(String race, String role);

    // Getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getSize() {
        return size;
    }

    public String getRace() {
        return race;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSize(String size) {
        this.size = size;
    }

    //other methods here if needed
}
