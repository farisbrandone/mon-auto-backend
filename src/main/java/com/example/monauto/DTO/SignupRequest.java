package com.example.monauto.DTO;

import com.example.monauto.entity.Role;
import com.example.monauto.enumFile.TypeSeller;
import com.example.monauto.enumFile.TypeSellerIdentificationDoc;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class SignupRequest {
    @NotBlank(message = "Nom is required")
    private String nom;
    @NotBlank(message = "Prenom is required")
    private String prenom;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Mot de passe is required")
    private String password;
    private String telephone;
    private String adresse;
    private String description;
    private Boolean activeState;
    //@NotBlank(message = "Ville requis")
    private String ville;
    //@NotBlank(message = "Pays requis")
    private String country;
    private String telephoneWhatsapp;
    private String typeSeller;
    private String typeSellerIdentificationDoc;
    private String identificationDocumentFile;
    private String dateOfCreated;
    private String dateOfModified;
    private Collection<String> roleSeller;
}
