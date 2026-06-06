package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Keluarga;
import com.example.demo.repository.KeluargaRepository;

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

    // ========== UPDATE PROFIL KELUARGA ==========
    @PutMapping("/update/{noKk}")
    public ResponseEntity<?> updateProfilKeluarga(@PathVariable String noKk, @RequestBody Keluarga keluargaUpdate) {
        Optional<Keluarga> keluargaOpt = keluargaRepository.findById(noKk);

        if (keluargaOpt.isPresent()) {
            Keluarga keluargaLama = keluargaOpt.get();
            
            // Timpa data lama dengan data baru yang dikirim dari form
            keluargaLama.setNamaKepala(keluargaUpdate.getNamaKepala());
            keluargaLama.setAlamat(keluargaUpdate.getAlamat());
            keluargaLama.setEmail(keluargaUpdate.getEmail());
            keluargaLama.setNoTelp(keluargaUpdate.getNoTelp());
            
            // Cek jika password ikut diubah (tidak kosong)
            if (keluargaUpdate.getPassword() != null && !keluargaUpdate.getPassword().isEmpty()) {
                keluargaLama.setPassword(keluargaUpdate.getPassword());
            }

            // Simpan kembali ke database
            keluargaRepository.save(keluargaLama);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Profil berhasil diupdate");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Data keluarga tidak ditemukan!");
            return ResponseEntity.badRequest().body(error);
        }
    }
}