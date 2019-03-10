package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

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

    public User(long id, String username, String password, String email) {
        setId(id);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
