package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum TypeSeller {
    PARTICULIER("PARTICULIER"),
    ENTREPRISE("ENTREPRISE");
    private final String type;
    TypeSeller(String type) {
        this.type = type;
    }
}
