package com.example.monauto.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private String marques;
    private String typesCarrosserie;
    private String anneeMin;
    private String anneeMax;
    private String kilometrageMin;
    private String kilometrageMax;
    private String PrixMin;
    private String PrixMax;
    private String typeMoteur;
    private String typeCarburant;
    private String selectedColor;
    private String keyWord;
    private String typeTransmission;
    private String villeDuBien;

}
