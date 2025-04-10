package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum TypeSellerIdentificationDoc {
    CNI("CNI"),
    PASSPORT("Passport");
    private final String value;
    TypeSellerIdentificationDoc(String value) {
        this.value = value;
    }
}
