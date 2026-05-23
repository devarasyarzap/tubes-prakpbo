package com.example.demo.controller;

import com.example.demo.dto.LaporanDTO;
import com.example.demo.service.LaporanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/laporan")
@CrossOrigin(origins = "*")
public class LaporanController {
    
    @Autowired
    private LaporanService laporanService;
    
    // ========== SESUAI PDF HALAMAN 15 ==========
    
    // GET /api/laporan/sampah - Tampilkan laporan total sampah (admin)
    @GetMapping("/sampah")
    public ResponseEntity<LaporanDTO> getLaporanSampah(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(laporanService.getLaporanSampah(start, end));
    }
    
    // GET /api/laporan/sampah/{noKk} - Hitung dampak lingkungan per keluarga
    @GetMapping("/sampah/{noKk}")
    public ResponseEntity<LaporanDTO> getDampakLingkungan(
            @PathVariable String noKk,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(laporanService.getDampakLingkunganPerKeluarga(noKk, start, end));
    }
    
    // ========== ENDPOINT TAMBAHAN (Opsional) ==========
    
    // Laporan keuangan
    @GetMapping("/keuangan")
    public ResponseEntity<LaporanDTO> getLaporanKeuangan(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(laporanService.getLaporanKeuangan(start, end));
    }
    
    // Laporan per wilayah
    @GetMapping("/wilayah")
    public ResponseEntity<LaporanDTO> getLaporanPerWilayah(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(laporanService.getLaporanPerWilayah(start, end));
    }
    
    // Laporan tren sampah
    @GetMapping("/tren")
    public ResponseEntity<LaporanDTO> getLaporanTren(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam(defaultValue = "harian") String periode) {
        return ResponseEntity.ok(laporanService.getLaporanTren(start, end, periode));
    }
}