package com.example.monauto.enumFile;

import lombok.Getter;

@Getter
public enum TypeMoteur {
  CYLINDRE4("4 Cylindres"),
  CYLINDRE6("6 Cylindres"),
  ELECTRIQUE("Electrique");

  private final String value;

    TypeMoteur(String value) {
      this.value = value;
    }
}
