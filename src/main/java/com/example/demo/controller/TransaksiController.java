package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StatusTransaksi;
import com.example.demo.model.TransaksiSampah;
import com.example.demo.repository.TransaksiRepository;

@RestController
@RequestMapping("/api/transaksi")
@CrossOrigin(origins = "*")
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @GetMapping
    public List<TransaksiSampah> getAll() {
        System.out.println("GET /api/transaksi dipanggil");
        return transaksiRepository.findAll();
    }

    // ========== KONFIRMASI BAYAR ==========
    // Menggunakan @RequestParam agar aman dari garis miring di ID Transaksi
    @PostMapping("/bayar")
    public Map<String, Object> bayar(@RequestParam("noTransaksi") String noTransaksi) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(">>> POST /api/transaksi/bayar dipanggil untuk TRX: " + noTransaksi);
        
        Optional<TransaksiSampah> transaksiOpt = transaksiRepository.findById(noTransaksi);
        
        if (transaksiOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Transaksi tidak ditemukan!");
            return response;
        }
        
        TransaksiSampah transaksi = transaksiOpt.get();
        
        if (transaksi.getStatus() == StatusTransaksi.sudah_bayar) {
            response.put("success", false);
            response.put("message", "Transaksi sudah lunas!");
            return response;
        }
        
        transaksi.setStatus(StatusTransaksi.sudah_bayar);
        transaksi.setTanggalBayar(LocalDateTime.now());
        transaksiRepository.save(transaksi);
        
        response.put("success", true);
        response.put("message", "Pembayaran berhasil dikonfirmasi!");
        return response;
    }

    // ========== HENTIKAN TRANSAKSI ==========
    @PostMapping("/henti")
    public Map<String, Object> henti(@RequestParam("noTransaksi") String noTransaksi) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(">>> POST /api/transaksi/henti dipanggil untuk TRX: " + noTransaksi);
        
        Optional<TransaksiSampah> transaksiOpt = transaksiRepository.findById(noTransaksi);
        
        if (transaksiOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Transaksi tidak ditemukan!");
            return response;
        }
        
        TransaksiSampah transaksi = transaksiOpt.get();
        
        // Asumsi kamu punya enum diberhentikan. Kalau beda, sesuaikan namanya ya!
        transaksi.setStatus(StatusTransaksi.diberhentikan); 
        transaksiRepository.save(transaksi);
        
        response.put("success", true);
        response.put("message", "Transaksi berhasil diberhentikan!");
        return response;
    }
}