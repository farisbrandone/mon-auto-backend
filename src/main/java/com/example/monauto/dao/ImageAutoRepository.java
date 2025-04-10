package com.example.monauto.dao;

import com.example.monauto.entity.ImageAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ImageAutoRepository extends JpaRepository<ImageAuto, Long> {
}
