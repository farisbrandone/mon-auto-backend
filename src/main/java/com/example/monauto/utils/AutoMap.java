package com.example.monauto.utils;

import com.example.monauto.DTO.AutoPostDto;
import com.example.monauto.entity.Auto;
import com.example.monauto.enumFile.*;

import java.time.Instant;
import java.util.Date;

public class AutoMap {
    private Auto auto;
    private AutoPostDto autoPostDto;

    public AutoMap(Auto auto, AutoPostDto autoPostDto) {
        this.auto = auto;
        this.autoPostDto = autoPostDto;
    }

    public  Auto getAutoWithAutoPostDto() {

        this.auto.setMarques(autoPostDto.getMarques());
        this.auto.setTypesCarrosserie(autoPostDto.getTypesCarrosserie());
        this.auto.setTypeCarburant(TypeCarburant.valueOf(autoPostDto.getTypeCarburant()) );
        this.auto.setTypeTransmission(TypeTransmission.valueOf(autoPostDto.getTypeTransmission()) );
        this.auto.setTypeDeTrainConducteur(autoPostDto.getTypeDeTrainConducteur());
        this.auto.setTypeMoteur(TypeMoteur.valueOf(autoPostDto.getTypeMoteur()) );
        this.auto.setKilometrage(  Float.parseFloat(autoPostDto.getKilometrage()));
        this.auto.setKilometrageUnit(autoPostDto.getKilometrageUnit());
        this.auto.setPrix(Float.parseFloat(autoPostDto.getPrix()));
        this.auto.setDevise(Devise.valueOf(autoPostDto.getDevise()) );
        this.auto.setImmatriculation(autoPostDto.getImmatriculation());
        this.auto.setAcceptsTerms(auto.isAcceptsTerms());
        this.auto.setCarteGriseUrl(autoPostDto.getCarteGriseUrl());
        this.auto.setPvControleTechniqueUrl(autoPostDto.getPvControleTechniqueUrl());
        this.auto.setStatusOfAuto(StatusOfAuto.valueOf(autoPostDto.getStatusOfAuto()));
        this.auto.setAnneeDeFabrication(GenerateToken.convertStringToDate(autoPostDto.getAnneeDeFabrication()));
        this.auto.setLastMaintenanceDate(GenerateToken.convertStringToDate(autoPostDto.getLastMaintenanceDate()));
         this.auto.setDateOfCreated(GenerateToken.convertStringToDate(autoPostDto.getDateOfCreated()));
         this.auto.setDateOfModified(GenerateToken.convertStringToDate(autoPostDto.getDateOfModified()));
         this.auto.setModel(autoPostDto.getModel());
         this.auto.setCouleurInt(autoPostDto.getCouleurInt());
         this.auto.setCouleurExt(autoPostDto.getCouleurExt());
         this.auto.setVilleDuBien(autoPostDto.getVilleDuBien());
         this.auto.setNbreDePlace(autoPostDto.getNbreDePlace());
         this.auto.setNbreDePorte(autoPostDto.getNbreDePorte());
        this.auto.setConso100kmAutoRoute(Float.parseFloat(autoPostDto.getConso100kmAutoRoute()));
         this.auto.setConso100kmVille(Float.parseFloat(autoPostDto.getConso100kmVille()));
         this.auto.setTailleDuMoteur(Float.parseFloat(autoPostDto.getTailleDuMoteur()));
        return this.auto;
    }
}
