package com.example.demo.service;

import com.example.demo.model.Keluarga;
import com.example.demo.repository.KeluargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeluargaService {

    @Autowired
    private KeluargaRepository keluargaRepository;

    public List<Keluarga> getAllKeluarga() {
        return keluargaRepository.findAll();
    }

    public void saveKeluarga(Keluarga keluarga) {
        keluargaRepository.save(keluarga);
    }
}