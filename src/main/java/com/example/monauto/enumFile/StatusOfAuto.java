package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum StatusOfAuto {
    ACTIVATE("activate"),
    DESACTIVATE("desactivate");
    private final String status;
    StatusOfAuto(String status) {
        this.status = status;
    }
}
