package com.example.monauto.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.monauto.DTO.ContactDTO;
import com.example.monauto.DTO.SignupRequest;
import com.example.monauto.utils.GenerateToken;
import com.example.monauto.utils.Utils;
import com.example.monauto.dao.RoleRepository;
import com.example.monauto.dao.SellerRepository;
import com.example.monauto.entity.Role;
import com.example.monauto.entity.Seller;
import com.example.monauto.enumFile.RoleUser;
import com.example.monauto.sec.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class SellerServiceImpl implements SellerService {
   private SellerRepository sellerRepository;
   private RoleRepository roleRepository;
   private PasswordEncoder passwordEncoder;
   private EmailService emailService;

    public SellerServiceImpl(SellerRepository sellerRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder, EmailService emailService
    ) {
        this.sellerRepository = sellerRepository;
        this.roleRepository = roleRepository;
         this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    @Override
    public Seller loadSellerByEmail(String email) {
        return sellerRepository.findSellerByEmail(email);
    }

    @Override
    public Seller addSeller(Seller seller) {
        String password = seller.getPassword();
        seller.setPassword(passwordEncoder.encode(password));
       return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteSeller(Seller seller) {
        sellerRepository.delete(seller);

    }

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Role loadRoleBySeller(Seller seller) {
        return null;
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public void addRoleToSeller(String sellerEmail, String roleName) {
        Seller seller = sellerRepository.findSellerByEmail(sellerEmail);
        RoleUser roleUser= RoleUser.valueOf(roleName);
        Role role = roleRepository.findByRoleName(roleUser);
        seller.getRoleSeller().add(role);

    }

    @Override
    public Seller signUp(Seller seller) {
        return null;
    }

    @Override
    public Seller registerUser(SignupRequest signupRequest) {


       if (sellerRepository.existsByEmail(signupRequest.getEmail())) {
           throw new RuntimeException("Email already in use");
       }

       Seller sellerInstance = new Seller();
        Collection<Role> roles = roleRepository.findAll();

        Collection<String> roleNames = roles.stream().map(role -> role.getRoleName().getRole()).toList();

        Utils utils = new Utils(sellerInstance, signupRequest);
        Seller seller=utils.getSellerWithSignupRequest();
        System.out.println(seller.getEmail());
       seller.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
       signupRequest.getRoleSeller().stream().forEach(role -> {
           if (roleNames.contains(role)) {
               RoleUser roleUser= RoleUser.valueOf(role);
               Role newRole =roleRepository.findByRoleName(roleUser);
               seller.getRoleSeller().add(newRole);
           }
       });
        if (Objects.equals(signupRequest.getPassword(), JwtUtils.ADMIN_PASSWORD) && Objects.equals(signupRequest.getEmail(), JwtUtils.ADMIN_EMAIL) ) {
            RoleUser roleUser= RoleUser.valueOf("ADMIN");
            Role newRole =roleRepository.findByRoleName(roleUser);
            seller.getRoleSeller().add(newRole);
        }
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.CONFIRM_EMAIL_SECRET);
        String jwtRefreshToken= GenerateToken.generateToken(seller);
        seller.setTokenConfirmation(jwtRefreshToken);
        //user.setTokenCreationDate(LocalDateTime.now());
        String subject = "Please confirm your registration";
        String text = "Thank you for registering. Please confirm your email by clicking the link below:\n\n"
                + "http://localhost:3000/seller-confirm-registration?token=" + jwtRefreshToken;

        emailService.sendConfirmationEmail(seller.getEmail(), subject, text);

        return sellerRepository.save(seller);
    }


    @Override
    public Seller verifySeller(String token) {
        Seller seller = sellerRepository.findSellerByTokenConfirmation(token);
        if (seller == null || seller.isActiveState()) {
            throw new RuntimeException("Email already in use");
        }
       /* if (user.getTokenCreationDate().isBefore(LocalDateTime.now().minusHours(24))) {
            return false;
        }*/

        seller.setActiveState(true);
        seller.setTokenConfirmation(null);
        sellerRepository.save(seller);
        return seller;
    }

    @Override
    public void initiatePasswordReset(String email) {
        Seller seller = sellerRepository.findSellerByEmail(email);
        if (seller == null) {
            throw new RuntimeException("User not found");
        }
        String token = GenerateToken.generateToken(seller);
        seller.setResetToken(token);
        seller.setResetTokenCreationDate(LocalDateTime.now());
        sellerRepository.save(seller);

        // Send email with reset link
        String resetUrl = "http://localhost:3000/seller-change-password?token=" + token;
        String subject = "Password Reset Request";
        String text = "To reset your password, please click the link below:\n\n" + resetUrl +
                "\n\nThis link will expire in 24 hours.";

        emailService.sendConfirmationEmail(seller.getEmail(), subject, text);
    }


 @Override
 public void sendContactEmailForAdmin (ContactDTO contact){
        String adminEmail="farisbrandone@yahoo.com";

     String subject = "Message de\n\n"+contact.getNom()+ "\n\nemail:\n\n"+ contact.getEmail()+ "\n\ntelephone:\n\n"+ contact.getTelephone();
     String text =contact.getMessage();
      try {
          emailService.sendConfirmationEmail(adminEmail, subject, text);
      }catch (Exception e){ throw new RuntimeException(e.getMessage());}

 }


    @Override
    public void resetPassword(String token, String newPassword) {
        Seller seller = sellerRepository.findSellerByResetToken(token);
                if (seller == null) {
                    throw new RuntimeException("Token not found");
                }

        // Check if token is expired (24 hours)
        if (seller.getResetTokenCreationDate().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new RuntimeException("Reset token has expired");
        }

        seller.setPassword(passwordEncoder.encode(newPassword));
        seller.setResetToken(null);
        seller.setResetTokenCreationDate(null);
        sellerRepository.save(seller);
    }





}
