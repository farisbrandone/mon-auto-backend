package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum Devise {
    FCFA("FCFA"),
    USD("USD"),
    EUR("EUR"),
    CAD("CAD");

    private final String devise;
    Devise(String role) {
        this.devise = role;
    }
}
