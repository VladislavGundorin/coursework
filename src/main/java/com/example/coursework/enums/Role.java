package com.example.coursework.enums;

public enum Role {
    ADMIN(1), USER(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }
}
