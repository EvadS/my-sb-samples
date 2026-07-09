package com.se.sample.service;


import com.se.sample.entity.Civilian;
import com.se.sample.repository.CivilianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CivilianService {

    private final CivilianRepository civilianRepository;

    public CivilianService(CivilianRepository civilianRepository) {
        this.civilianRepository = civilianRepository;
    }

    public List<Civilian> findAll() {
        return civilianRepository.findAll();
    }
}
