package com.example.demo.controller;

import com.example.demo.model.SampahMasuk;
import com.example.demo.service.SampahMasukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SampahMasukController {
    
    @Autowired
    private SampahMasukService sampahMasukService;
    
    // ========== SESUAI PDF ==========
    
    // POST /api/sampah-masuk - Catat setoran sampah masuk
    @PostMapping("/sampah-masuk")
    public ResponseEntity<SampahMasuk> catatSetoran(@RequestBody SampahMasuk sampahMasuk) {
        SampahMasuk created = sampahMasukService.create(sampahMasuk);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    // GET /api/sampah-masuk/keluarga/{noKk} - Lihat riwayat setoran per keluarga
    @GetMapping("/sampah-masuk/keluarga/{noKk}")
    public ResponseEntity<List<SampahMasuk>> getRiwayatByKeluarga(@PathVariable String noKk) {
        return ResponseEntity.ok(sampahMasukService.getByKeluarga(noKk));
    }
    
    // GET /api/laporan/sampah - Tampilkan laporan total sampah (admin)
    @GetMapping("/laporan/sampah")
    public ResponseEntity<Map<String, Object>> getLaporanSampah(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(sampahMasukService.getLaporanSampah(start, end));
    }
    
    // GET /api/laporan/sampah/{noKk} - Hitung dampak lingkungan per keluarga
    @GetMapping("/laporan/sampah/{noKk}")
    public ResponseEntity<Map<String, Object>> getDampakLingkungan(@PathVariable String noKk) {
        return ResponseEntity.ok(sampahMasukService.getDampakLingkungan(noKk));
    }
    
    // ========== CRUD Tambahan ==========
    
    @GetMapping("/sampah-masuk")
    public ResponseEntity<List<SampahMasuk>> getAll() {
        return ResponseEntity.ok(sampahMasukService.getAll());
    }
    
    @GetMapping("/sampah-masuk/{noSampah}")
    public ResponseEntity<SampahMasuk> getById(@PathVariable Integer noSampah) {
        SampahMasuk data = sampahMasukService.getById(noSampah);
        if (data != null) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/sampah-masuk/{noSampah}")
    public ResponseEntity<SampahMasuk> update(@PathVariable Integer noSampah, 
                                               @RequestBody SampahMasuk updatedData) {
        SampahMasuk updated = sampahMasukService.update(noSampah, updatedData);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/sampah-masuk/{noSampah}")
    public ResponseEntity<Void> delete(@PathVariable Integer noSampah) {
        if (sampahMasukService.delete(noSampah)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}