package com.example.monauto.entity;

import com.example.monauto.enumFile.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
public class Auto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String carteGriseUrl;
    private String pvControleTechniqueUrl;
    private Float prix;

    @Enumerated(EnumType.STRING)
    private Devise devise;
    private String marques;
    private String typesCarrosserie;
    private Date anneeDeFabrication;
    private String categorie;
    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;
    @Enumerated(EnumType.STRING)
    private TypeMoteur typeMoteur;
    private Date dateOfCreated;
    private Date dateOfModified;
    private Float kilometrage;
    private String kilometrageUnit;
    @Enumerated(EnumType.STRING)
    private TypeTransmission typeTransmission;
    private Date lastMaintenanceDate;
    private String typeDeTrainConducteur;
    private int nbreDePlace;
    private int nbreDePorte;
    @Enumerated(EnumType.STRING)
    private StatusOfAuto statusOfAuto;
    private String villeDuBien;
    private String paysDuBien;
    @ManyToOne
    private  Seller seller;
    private boolean acceptsTerms;
    private String immatriculation;
    private String model;
    private String couleurExt;
    private String couleurInt;
    private Float conso100kmAutoRoute;
    private Float conso100kmVille;
    private Float tailleDuMoteur;

    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private Collection<ImageAuto> imagesAuto=new ArrayList<>();
}
