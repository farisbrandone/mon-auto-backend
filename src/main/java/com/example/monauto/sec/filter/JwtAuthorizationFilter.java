package com.example.monauto.sec.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.monauto.sec.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/refreshToken")){
            filterChain.doFilter(request, response);
        }else {
            String authorizationToken=request.getHeader(JwtUtils.AUTH_HEADER);
            if(authorizationToken!=null && authorizationToken.startsWith(JwtUtils.BEARER_HEADER)){
                try {

                    String token = authorizationToken.substring(JwtUtils.BEARER_HEADER.length());
                    Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class); /*as array(string.class) pour lui dire que le json convertit en object java est un tableau de string*/
                    Collection<GrantedAuthority> authorities =new  ArrayList<>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken); /*verifie les donner avec ceus de la session*/
                    filterChain.doFilter(request, response); /*puisque tout est bonc passe au filtre suivant*/
                } catch (Exception e) {
                    response.setHeader("error-message", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }



            } else{
                /*si pas de bearer peut passer mais c'est spring qui va te gerer puisque pas authoriser peut etre*/
                filterChain.doFilter(request, response);
            }
        }

    }
}
