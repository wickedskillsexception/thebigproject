package com.siit.thebigproject.domain;

public class User extends ObjectId {
    private String username;
    private String password;
    private String email;
    private int desiredCalories;
    private String desiredRecipeTypes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDesiredCalories() {
        return desiredCalories;
    }

    public void setDesiredCalories(int desiredCalories) {
        this.desiredCalories = desiredCalories;
    }

    public String getDesiredRecipeTypes() {
        return desiredRecipeTypes;
    }

    public void setDesiredRecipeTypes(String desiredRecipeTypes) {
        this.desiredRecipeTypes = desiredRecipeTypes;
    }

    //add fridge by composition, user can have 0 ingredients and just wants recipes



}
