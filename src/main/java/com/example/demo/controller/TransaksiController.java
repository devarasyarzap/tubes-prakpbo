package com.example.demo.controller;

import com.example.demo.model.StatusTransaksi;
import com.example.demo.model.TransaksiSampah;
import com.example.demo.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransaksiController {
    
    @Autowired
    private TransaksiService transaksiService;
    
    // ========== SESUAI PDF ==========
    
    // POST /api/transaksi - Catat pembayaran transaksi sampah
    @PostMapping("/transaksi")
    public ResponseEntity<TransaksiSampah> catatPembayaran(@RequestBody Map<String, Object> request) {
        String noTransaksi = (String) request.get("noTransaksi");
        Double jumlahBayar = ((Number) request.get("jumlahBayar")).doubleValue();
        
        TransaksiSampah transaksi = transaksiService.catatPembayaran(noTransaksi, jumlahBayar);
        if (transaksi != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(transaksi);
        }
        return ResponseEntity.notFound().build();
    }
    
    // GET /api/transaksi/keluarga/{noKk} - Lihat riwayat transaksi per keluarga
    @GetMapping("/transaksi/keluarga/{noKk}")
    public ResponseEntity<List<TransaksiSampah>> getRiwayatByKeluarga(@PathVariable String noKk) {
        return ResponseEntity.ok(transaksiService.getByKeluarga(noKk));
    }
    
    // PUT /api/transaksi/{noTransaksi} - Update status transaksi
    @PutMapping("/transaksi/{noTransaksi}")
    public ResponseEntity<TransaksiSampah> updateStatus(
            @PathVariable String noTransaksi,
            @RequestBody Map<String, String> request) {
        String statusBaru = request.get("status");
        TransaksiSampah updated = transaksiService.updateStatus(noTransaksi, statusBaru);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    // ========== CRUD Tambahan ==========
    
    @GetMapping("/transaksi")
    public ResponseEntity<List<TransaksiSampah>> getAll() {
        return ResponseEntity.ok(transaksiService.getAll());
    }
    
    @GetMapping("/transaksi/{noTransaksi}")
    public ResponseEntity<TransaksiSampah> getById(@PathVariable String noTransaksi) {
        Optional<TransaksiSampah> transaksi = transaksiService.getById(noTransaksi);
        return transaksi.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/transaksi/create")
    public ResponseEntity<TransaksiSampah> create(@RequestBody TransaksiSampah transaksi) {
        TransaksiSampah created = transaksiService.create(transaksi);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/transaksi/update/{noTransaksi}")
    public ResponseEntity<TransaksiSampah> update(@PathVariable String noTransaksi,
                                                   @RequestBody TransaksiSampah updatedData) {
        TransaksiSampah updated = transaksiService.update(noTransaksi, updatedData);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/transaksi/{noTransaksi}")
    public ResponseEntity<Void> delete(@PathVariable String noTransaksi) {
        if (transaksiService.delete(noTransaksi)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // ========== ENDPOINT TAMBAHAN UNTUK LAPORAN ==========
    
    // Get transaksi by status
    @GetMapping("/transaksi/status/{status}")
    public ResponseEntity<List<TransaksiSampah>> getByStatus(@PathVariable String status) {
        try {
            StatusTransaksi statusEnum = StatusTransaksi.valueOf(status);
            return ResponseEntity.ok(transaksiService.getByStatus(statusEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Cek tunggakan keluarga
    @GetMapping("/transaksi/tunggakan/{noKk}")
    public ResponseEntity<Map<String, Object>> cekTunggakan(@PathVariable String noKk) {
        boolean hasTunggakan = transaksiService.hasTunggakan(noKk);
        Map<String, Object> response = new HashMap<>();
        response.put("no_kk", noKk);
        response.put("memiliki_tunggakan", hasTunggakan);
        response.put("status", hasTunggakan ? "Perlu segera dibayar" : "Lunas");
        return ResponseEntity.ok(response);
    }
    
    // Statistik transaksi (untuk dashboard admin)
    @GetMapping("/transaksi/statistik")
    public ResponseEntity<Map<String, Object>> getStatistik() {
        return ResponseEntity.ok(transaksiService.getStatistik());
    }
    
    // Laporan keuangan per periode
    @GetMapping("/transaksi/laporan-keuangan")
    public ResponseEntity<Map<String, Object>> getLaporanKeuangan(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(transaksiService.getLaporanKeuangan(start, end));
    }
}