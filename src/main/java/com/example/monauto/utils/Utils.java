package com.example.monauto.utils;

import com.example.monauto.DTO.SignupRequest;
import com.example.monauto.entity.Seller;
import com.example.monauto.enumFile.TypeSeller;
import com.example.monauto.enumFile.TypeSellerIdentificationDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Utils {
    private Seller seller;
    private SignupRequest signupRequest;

    public Seller getSellerWithSignupRequest() {
        this.seller.setEmail(signupRequest.getEmail());
        this.seller.setNom(signupRequest.getNom());
        this.seller.setPrenom(signupRequest.getPrenom());
        this.seller.setTypeSeller(TypeSeller.valueOf(signupRequest.getTypeSeller()));
        this.seller.setAdresse(signupRequest.getAdresse());
        this.seller.setTelephone(signupRequest.getTelephone());
        this.seller.setTelephoneWhatsapp(signupRequest.getTelephoneWhatsapp());
        this.seller.setTypeSellerIdentificationDoc(TypeSellerIdentificationDoc.valueOf(signupRequest.getTypeSellerIdentificationDoc()));
        this.seller.setCountry(signupRequest.getCountry());
        this.seller.setVille(signupRequest.getVille());
        this.seller.setActiveState(signupRequest.getActiveState());
        this.seller.setPassword(signupRequest.getPassword());
        this.seller.setDescription(signupRequest.getDescription());
       this.seller.setIdentificationDocumentFile(signupRequest.getIdentificationDocumentFile());
        this.seller.setDateOfCreated(GenerateToken.convertStringToDate(signupRequest.getDateOfCreated()).toInstant());
        this.seller.setDateOfModified(GenerateToken.convertStringToDate(signupRequest.getDateOfModified()).toInstant());
        return seller;

    }

}
