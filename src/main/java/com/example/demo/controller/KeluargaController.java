package com.example.demo.controller;

import com.example.demo.model.Keluarga;
import com.example.demo.repository.KeluargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keluarga")
@CrossOrigin(origins = "*")
public class KeluargaController {

    @Autowired
    private KeluargaRepository keluargaRepository;

    // GET semua keluarga
    @GetMapping
    public ResponseEntity<List<Keluarga>> getAllKeluarga() {
        List<Keluarga> keluargaList = keluargaRepository.findAll();
        return ResponseEntity.ok(keluargaList);
    }
}