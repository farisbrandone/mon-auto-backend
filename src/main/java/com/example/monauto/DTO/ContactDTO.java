package com.example.monauto.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ContactDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String nom;
    private  String prenom;
    private String telephone;
    private String message;



}
