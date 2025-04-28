package com.example.monauto.web;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.monauto.DTO.*;
import com.example.monauto.dao.AutoRepository;
import com.example.monauto.dao.SellerRepository;
import com.example.monauto.entity.Auto;
import com.example.monauto.entity.Role;
import com.example.monauto.entity.Seller;
import com.example.monauto.enumFile.TypeMoteur;
import com.example.monauto.service.IAutoService;
import com.example.monauto.service.IRoleService;
import com.example.monauto.service.RoleServiceImpl;
import com.example.monauto.service.SellerService;
import com.example.monauto.utils.Convertion;
import com.example.monauto.utils.SearchCarSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class SellerRestController {
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;
    private final AutoRepository autoRepository;
    private final IRoleService roleService;
    private final IAutoService autoService;
    public SellerRestController(SellerService sellerService, SellerRepository sellerRepository, AutoRepository autoRepository, RoleServiceImpl roleService, IAutoService autoService) {
        this.sellerService = sellerService;
        this.sellerRepository = sellerRepository;
        this.autoRepository = autoRepository;
        this.roleService = roleService;
        this.autoService = autoService;
    }


    @GetMapping("/newsearch")
    public ResponseEntity<PaginatedCarResponse> newsearchCars(
            @ModelAttribute SearchDto criteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {


        String marques=criteria.getMarques();
        String typesCarrosserie=criteria.getTypesCarrosserie();
        Date anneeMin= criteria.getAnneeMin()!=null ?Convertion.convertStringToDate( criteria.getAnneeMin()):null;
        Date anneeMax=criteria.getAnneeMax()!=null ? Convertion.convertStringToDate( criteria.getAnneeMax()):null;
        Float kilometrageMin=criteria.getKilometrageMin()!=null? Convertion.convertStringToFloat(criteria.getKilometrageMin()):null;
        Float kilometrageMax=criteria.getKilometrageMax()!=null? Convertion.convertStringToFloat(criteria.getKilometrageMax()):null;
        Float PrixMin=criteria.getPrixMin()!=null? Convertion.convertStringToFloat(criteria.getPrixMin()):null;
        Float PrixMax=criteria.getPrixMin()!=null? Convertion.convertStringToFloat(criteria.getPrixMax()):null;
        String typeMoteur=criteria.getTypeMoteur();
        String typeCarburant=criteria.getTypeCarburant();
        String keyword= criteria.getKeyWord();
        String selectedColor=criteria.getSelectedColor();

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Auto> result = autoRepository.searchCars(
               marques,
                typesCarrosserie,
                anneeMin,
                anneeMax,
                kilometrageMin,
                kilometrageMax,
                PrixMin,
                PrixMax,
                typeMoteur,
                typeCarburant,
                selectedColor,
                keyword,
                pageable
        );
        List<Auto> autos = new ArrayList<>();
        if (result != null && result.hasContent()) {  // Double vérification
            autos = new ArrayList<>(result.getContent());
            System.out.println("zouzou5");// Conversion explicite
        }

        autos.forEach(auto -> {
            if (auto.getId() != null) {

                System.out.println("Extension pour l'auto ID " + auto.getMarques()+ ": ");

            }
        });

        System.out.println( result.isLast()+"bobo1" );
        System.out.println( result.getTotalPages()+"bobo2" );
        System.out.println( result.isLast()+"bobo1" );

        PaginatedCarResponse response = new PaginatedCarResponse(
                autos,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );

        //System.out.println(response);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/search")
    public ResponseEntity<PaginatedCarResponse> searchCars(@ModelAttribute SearchDto criteria, @RequestParam(defaultValue = "0") int page,

                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                 @RequestParam(defaultValue = "asc") String direction) {

        System.out.println(criteria.toString());

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Auto> carPage = autoRepository.findAll(SearchCarSpecification.withCriteria(criteria), pageable);
        PaginatedCarResponse response = new PaginatedCarResponse(
                carPage.getContent(),
                carPage.getNumber(),
                carPage.getSize(),
                carPage.getTotalElements(),
                carPage.getTotalPages(),
                carPage.isLast()
        );
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/addRole")
    public Collection<Role> addRole(@RequestBody Collection<String> role) {
        return roleService.addRole(role);

    }


    @PostMapping(path = "/addAuto")
    public Auto addAuto(@RequestBody AutoPostDto autoPost) {

        return autoService.addOneAuto(autoPost);
    }



 @PostMapping(path = "/signup")
 public ResponseEntity<Seller> signup(@RequestBody SignupRequest seller) {

        try {

             Seller seller1=sellerService.registerUser(seller);
            return ResponseEntity.ok(seller1);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }

 }


 @GetMapping("/confirm")
 public ResponseEntity<Seller> confirm(@RequestParam("token") String token) {

        try {
            Seller seller=sellerService.verifySeller(token);
            System.out.println("verified1 : "+seller.getCountry());

            System.out.println("verified2 : "+seller.getEmail());
            return ResponseEntity.ok(seller);
        } catch (RuntimeException e) {
            System.out.println("verified3 : ");
            return ResponseEntity.badRequest().body(null);
        }

 }


    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletResponse response, HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization");
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            try {
                refreshToken = refreshToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                Seller seller= sellerService.loadSellerByEmail(email);
                String jwtAccessToken = JWT.create()
                        .withSubject(seller.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURL().toString()) /*celui à l'origine du token*/
                        .withClaim("roles", seller.getRoleSeller().stream()
                                .map(ga ->ga.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", refreshToken);
                response.setContentType("application/json");

                /*objectMapper() utiliser pour sérialiser un map en json*/
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {
                response.setHeader("error-message", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else {
            throw new RuntimeException("Refresh token is invalid");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        try {
            sellerService.initiatePasswordReset(request.getEmail());
            return ResponseEntity.ok("Password reset link has been sent to your email");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordDTO request) {
        try {
            sellerService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok("Password has been reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestParam String token) {
        try {
            Seller seller = sellerRepository.findSellerByResetToken(token);

                    if (seller==null) {
                        return ResponseEntity.badRequest().body("Invalid token");
                    }

            if (seller.getResetTokenCreationDate().isBefore(LocalDateTime.now().minusHours(24))) {
                throw new RuntimeException("Reset token has expired");
            }

            return ResponseEntity.ok("Token is valid");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sendContact")
    public ResponseEntity<?> sendContact(@Valid @RequestBody ContactDTO request) {
        try {
            sellerService.sendContactEmailForAdmin(request);
            return ResponseEntity.ok("Email has been send successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
