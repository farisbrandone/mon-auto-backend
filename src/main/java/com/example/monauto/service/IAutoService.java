package com.example.monauto.service;

import com.example.monauto.DTO.AutoPostDto;
import com.example.monauto.entity.Auto;

public interface IAutoService {
    public void initAuto();
    public void initSeller();
    public Auto addOneAuto(AutoPostDto auto);
}
