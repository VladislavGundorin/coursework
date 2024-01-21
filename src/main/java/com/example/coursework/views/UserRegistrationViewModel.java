package com.example.coursework.views;

import com.example.coursework.validation.validUser.UniqueUsername;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistrationViewModel {
    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    @UniqueUsername
    private String username;


    private String firstName;

    private String lastName;

    private String password;

    public UserRegistrationViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Full name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "Full name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserRegistrationViewModel{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}