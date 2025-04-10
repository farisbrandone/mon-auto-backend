package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum RoleUser {
    ADMIN("ADMIN"),
    USER("USER"),
    PARTICULIER("PARTICULIER"),
    ENTREPRISE("ENTREPRISE"),
    STUDYACTIVATION("STUDYACTIVATION"),
    DESACTIVATE("DESACTIVATE");

    private final String role;
    RoleUser(String role) {
        this.role = role;
    }
}
