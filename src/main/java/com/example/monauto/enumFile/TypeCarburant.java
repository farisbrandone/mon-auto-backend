package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum TypeCarburant {
    ESSENCE("Essence"),
    DIESEL("Diesel"),
    ELECTRIQUE("Electrique"),
    HYBRIDE("Hybride"),
    GPL("GPL");



    private final String value;

    TypeCarburant(String value) {
        this.value = value;
    }

}
