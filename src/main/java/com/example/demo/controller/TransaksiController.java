package com.example.demo.controller;

import com.example.demo.model.StatusTransaksi;
import com.example.demo.model.TransaksiSampah;
import com.example.demo.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @PostMapping("/bayar/{noTransaksi}")
    public Map<String, Object> bayar(@PathVariable String noTransaksi) {
        Map<String, Object> response = new HashMap<>();
        
        System.out.println(">>> POST /api/transaksi/bayar/" + noTransaksi + " dipanggil");
        
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
}