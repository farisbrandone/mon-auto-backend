package com.example.monauto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoPostDto {
    private String   marques;
    private String   typesCarrosserie;
    private String   typeCarburant;
    private String   typeTransmission;
    private String    typeDeTrainConducteur;
    private String    typeMoteur;
    private String   kilometrage;
    private String   kilometrageUnit;
    private String   prix;
    private String   devise;
    private String   immatriculation;
    private String   acceptsTerms;
    private String    carteGriseUrl;
    private String   pvControleTechniqueUrl;
    private String[]  imagesAuto;
    private String    statusOfAuto;
    private String    anneeDeFabrication;
    private String     lastMaintenanceDate;
    private String    dateOfCreated;
    private String   dateOfModified;
    private String userToken;
    private String model;
    private String couleurExt;
    private String couleurInt;
    private String villeDuBien;
    private int nbreDePlace;
    private int nbreDePorte;
    private String conso100kmAutoRoute;
    private String conso100kmVille;
    private String tailleDuMoteur;

}
