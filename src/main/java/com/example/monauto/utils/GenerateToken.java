package com.example.monauto.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.monauto.entity.Seller;
import com.example.monauto.sec.JwtUtils;

import java.time.Instant;
import java.util.Date;

public class GenerateToken {
    public static String generateToken(Seller seller) {
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.CONFIRM_EMAIL_SECRET);
        return JWT.create()
                .withSubject(seller.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtUtils.EXPIRE_REFRESH_TOKEN))
                .sign(algorithm);
    }

    public static Date convertStringToDate(String date) {
        Instant instant = Instant.parse(date);

        // Or convert to java.util.Date
        return Date.from(instant);
    }

}
