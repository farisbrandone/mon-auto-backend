package com.example.monauto.sec;

import com.example.monauto.sec.filter.JwtAuthenticationFilter;
import com.example.monauto.sec.filter.JwtAuthorizationFilter;
import com.example.monauto.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
    };

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authManager);
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(httpSecurityHeadersConfigurer ->httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(authorizeRequests ->authorizeRequests.requestMatchers("/h2-console/**", "/refreshToken/**", "/forgot-password","/reset-password", "/signup", "/validate-reset-token", "/confirm", "/swagger-ui/**", "/addAuto", "/sendContact").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers(SWAGGER_WHITELIST).permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET,"/autos/**", "/search/**", "/newsearch" ).permitAll());
       /* http.csrf(csrf -> csrf
                .ignoringRequestMatchers(SWAGGER_WHITELIST) // Disable CSRF for Swagger
                .ignoringRequestMatchers("/api/auth/**")    // And your public endpoints
        );*/
        http.authorizeHttpRequests(authorizeRequests ->authorizeRequests.anyRequest().authenticated());
        http.addFilter(jwtAuthenticationFilter);
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}


