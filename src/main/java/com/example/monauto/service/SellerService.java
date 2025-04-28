package com.example.monauto.service;

import com.example.monauto.DTO.ContactDTO;
import com.example.monauto.DTO.SignupRequest;
import com.example.monauto.entity.Role;
import com.example.monauto.entity.Seller;

import java.util.List;

public interface SellerService {
    Seller loadSellerByEmail(String email);
    Seller addSeller(Seller seller);
    Seller updateSeller(Seller seller);
    void deleteSeller(Seller seller);
    List<Seller> getAllSellers();
    Role loadRoleBySeller(Seller seller);
    Role addRole(Role role);
    Role updateRole(Role role);
    void addRoleToSeller(String sellerEmail, String roleName);
    Seller signUp(Seller seller);
    Seller registerUser(SignupRequest signupRequest);
    public Seller verifySeller(String token);
    public void initiatePasswordReset(String email);
    public void resetPassword(String token, String newPassword);
    public void sendContactEmailForAdmin(ContactDTO contactDTO);
}
