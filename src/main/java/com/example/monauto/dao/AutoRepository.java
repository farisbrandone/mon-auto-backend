package com.example.monauto.dao;

import com.example.monauto.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource
@CrossOrigin("*")
public interface AutoRepository  extends JpaRepository<Auto, Long> {
}
