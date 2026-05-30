package com.example.demo.controller;

import com.example.demo.model.JenisSampah;
import com.example.demo.model.SampahMasuk;
import com.example.demo.repository.JenisSampahRepository;
import com.example.demo.repository.SampahMasukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sampah-masuk")
@CrossOrigin(origins = "*")
public class SampahMasukController {

    @Autowired
    private SampahMasukRepository sampahMasukRepository;
    
    @Autowired
    private JenisSampahRepository jenisSampahRepository;

    // ========== GET ALL (untuk dashboard pegawai) ==========
    @GetMapping
    public ResponseEntity<List<SampahMasuk>> getAllSampahMasuk() {
        List<SampahMasuk> data = sampahMasukRepository.findAll();
        return ResponseEntity.ok(data);
    }
    
    // ========== GET BY KK (untuk riwayat keluarga) ==========
    @GetMapping("/keluarga/{noKk}")
    public ResponseEntity<List<SampahMasuk>> getByKeluarga(@PathVariable String noKk) {
        List<SampahMasuk> data = sampahMasukRepository.findByNoKk(noKk);
        return ResponseEntity.ok(data);
    }

    // ========== CREATE NEW ==========
    @PostMapping
    public ResponseEntity<?> createSampahMasuk(@RequestBody Map<String, Object> request) {
        try {
            // Ambil data dari request
            Integer jenisSampahId = (Integer) request.get("jenisSampahId");
            Integer jumlahMasuk = (Integer) request.get("jumlahMasuk");
            String noKk = (String) request.get("noKk");
            
            // Cari JenisSampah berdasarkan ID
            JenisSampah jenisSampah = jenisSampahRepository.findById(jenisSampahId)
                .orElseThrow(() -> new RuntimeException("Jenis sampah tidak ditemukan"));
            
            // Buat entity baru
            SampahMasuk sampahMasuk = new SampahMasuk();
            sampahMasuk.setJenisSampah(jenisSampah);
            sampahMasuk.setJumlahMasuk(jumlahMasuk);
            sampahMasuk.setNoKk(noKk);
            sampahMasuk.setTanggalMasuk(LocalDateTime.now());
            
            SampahMasuk saved = sampahMasukRepository.save(sampahMasuk);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Setoran berhasil disimpan");
            response.put("data", saved);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}