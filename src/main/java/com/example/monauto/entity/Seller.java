package com.example.monauto.entity;

import com.example.monauto.enumFile.RoleUser;
import com.example.monauto.enumFile.TypeSeller;
import com.example.monauto.enumFile.TypeSellerIdentificationDoc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Seller {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "Nom requis")
    private String nom;
    //@NotBlank(message = "Prenom requis")
    private String prenom;
    //@NotBlank(message = "email requis")
    @Column(unique = true)
    private String email;
    private String description;
    //@NotBlank(message = "Mot de passe requis")
    private String password;
    private String telephone;
    private String adresse;
    //@NotBlank(message = "Ville requis")
    private String ville;
    //@NotBlank(message = "Pays requis")
    private String country;
    private String telephoneWhatsapp;
    //@NotBlank(message = "Role utilisateur requis")
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roleSeller=new ArrayList<>();
    //@NotBlank(message = "Type seller requis")
    @Enumerated(EnumType.STRING)
    private TypeSeller typeSeller;
    //@NotBlank(message = "Type identifiant document requis")
    @Enumerated(EnumType.STRING)
    private TypeSellerIdentificationDoc typeSellerIdentificationDoc;
    private String identificationDocumentFile;


    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Auto> autos;
    private boolean activeState=false;
    private String tokenConfirmation;
    private String image;
    private String resetToken;
    private LocalDateTime resetTokenCreationDate;

    @CreatedDate
    @Column(nullable = true, updatable = false)
    private Instant dateOfCreated;

    @LastModifiedDate
    @Column(nullable = true)
    private Instant dateOfModified;
   /* @Column(name = "token_creation_date")
    private LocalDateTime tokenCreationDate;*/
}
