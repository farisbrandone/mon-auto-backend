package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum TypeTransmission {
    TRANSMISSION_MANUELLE("Transmission manuelle"),
    TRANSMISSION_AUTOMATIQUE("Transmission automatique"),
    TRANSMISSION_SEMI_AUTOMATIQUE("Transmission semi automatique");

    private final String value;

    TypeTransmission(String value) {
        this.value = value;
    }
}
