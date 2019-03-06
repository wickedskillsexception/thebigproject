package com.siit.thebigproject.domain;

//add fridge by composition, templates can have 0 ingredients and just wants recipes

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class User extends ObjectId {
    @NotEmpty(message = "Please insert the username")
    @Length(max = 50)
    private String username;

    @NotNull
    @Length (min = 6)
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Max(5000)
    private int desiredCalories;


    private String desiredRecipeType;

    @NotNull
    private Fridge fridge;

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

    public String getDesiredRecipeType() {
        return desiredRecipeType;
    }

    public void setDesiredRecipeType(String desiredRecipeType) {
        this.desiredRecipeType = desiredRecipeType;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", desiredCalories=" + desiredCalories +
                ", desiredRecipeType='" + desiredRecipeType + '\'' +
                ", fridge=" + fridge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return desiredCalories == user.desiredCalories &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(desiredRecipeType, user.desiredRecipeType) &&
                Objects.equals(fridge, user.fridge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, desiredCalories, desiredRecipeType, fridge);
    }
}
