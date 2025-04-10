package com.example.monauto.dao;

import com.example.monauto.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource
@CrossOrigin("*")
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findSellerByEmail(String email);
    boolean existsByEmail(String email);
    Seller findSellerByTokenConfirmation(String tokenConfirmation);
    Seller findSellerByResetToken(String resetToken);
}
