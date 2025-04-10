package com.example.monauto;

import com.example.monauto.entity.Auto;
import com.example.monauto.entity.Seller;
import com.example.monauto.service.IAutoService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MonAutoApplication implements CommandLineRunner {

    private  IAutoService autoService;

    private  RepositoryRestConfiguration restConfiguration;

    public MonAutoApplication(IAutoService autoService, RepositoryRestConfiguration restConfiguration) {
        this.autoService = autoService;
        this.restConfiguration = restConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(MonAutoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Auto.class, Seller.class);
        autoService.initSeller();
        autoService.initAuto();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
