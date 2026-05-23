package com.example.demo.service;

import com.example.demo.model.StatusTransaksi;
import com.example.demo.model.TransaksiSampah;
import com.example.demo.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransaksiService {
    
    @Autowired
    private TransaksiRepository transaksiRepository;
    
    // Harga per kg sampah (bisa disesuaikan)
    private static final double HARGA_PER_KG = 2000;
    
    // ========== CRUD ==========
    
    public List<TransaksiSampah> getAll() {
        return transaksiRepository.findAll();
    }
    
    public Optional<TransaksiSampah> getById(String noTransaksi) {
        return transaksiRepository.findById(noTransaksi);
    }
    
    @Transactional
    public TransaksiSampah create(TransaksiSampah transaksi) {
        // Auto generate nomor transaksi via @PrePersist
        return transaksiRepository.save(transaksi);
    }
    
    @Transactional
    public TransaksiSampah update(String noTransaksi, TransaksiSampah updatedData) {
        Optional<TransaksiSampah> existingOpt = transaksiRepository.findById(noTransaksi);
        if (existingOpt.isPresent()) {
            TransaksiSampah existing = existingOpt.get();
            existing.setNoKk(updatedData.getNoKk());
            existing.setStatus(updatedData.getStatus());
            // Tanggal bayar otomatis di setStatus()
            return transaksiRepository.save(existing);
        }
        return null;
    }
    
    @Transactional
    public boolean delete(String noTransaksi) {
        if (transaksiRepository.existsById(noTransaksi)) {
            transaksiRepository.deleteById(noTransaksi);
            return true;
        }
        return false;
    }
    
    // ========== Query Khusus (Sesuai PDF) ==========
    
    // POST /api/transaksi - Catat pembayaran transaksi sampah
    @Transactional
    public TransaksiSampah catatPembayaran(String noTransaksi, Double jumlahBayar) {
        Optional<TransaksiSampah> transaksiOpt = transaksiRepository.findById(noTransaksi);
        if (transaksiOpt.isPresent()) {
            TransaksiSampah transaksi = transaksiOpt.get();
            transaksi.setStatus(StatusTransaksi.sudah_bayar);
            // Bisa tambahkan field jumlahBayar jika diperlukan
            return transaksiRepository.save(transaksi);
        }
        return null;
    }
    
    // GET /api/transaksi/keluarga/{noKk} - Lihat riwayat transaksi per keluarga
    public List<TransaksiSampah> getByKeluarga(String noKk) {
        return transaksiRepository.findByNoKk(noKk);
    }
    
    // PUT /api/transaksi/{noTransaksi} - Update status transaksi
    @Transactional
    public TransaksiSampah updateStatus(String noTransaksi, String statusBaru) {
        Optional<TransaksiSampah> transaksiOpt = transaksiRepository.findById(noTransaksi);
        if (transaksiOpt.isPresent()) {
            TransaksiSampah transaksi = transaksiOpt.get();
            transaksi.setStatus(StatusTransaksi.valueOf(statusBaru));
            return transaksiRepository.save(transaksi);
        }
        return null;
    }
    
    // ========== Laporan & Dashboard ==========
    
    // Get transaksi by status
    public List<TransaksiSampah> getByStatus(StatusTransaksi status) {
        return transaksiRepository.findByStatus(status);
    }
    
    // Cek status tunggakan keluarga
    public boolean hasTunggakan(String noKk) {
        return transaksiRepository.existsByNoKkAndStatus(noKk, StatusTransaksi.belum_bayar);
    }
    
    // Get statistik transaksi
    public Map<String, Object> getStatistik() {
        List<Object[]> results = transaksiRepository.countByStatus();
        Map<String, Long> statistik = new HashMap<>();
        
        for (Object[] row : results) {
            StatusTransaksi status = (StatusTransaksi) row[0];
            Long count = (Long) row[1];
            statistik.put(status.name(), count);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("statistik", statistik);
        response.put("total_transaksi", transaksiRepository.count());
        
        return response;
    }
    
    // Get laporan keuangan
    public Map<String, Object> getLaporanKeuangan(LocalDateTime start, LocalDateTime end) {
        Double pendapatan = transaksiRepository.getTotalPendapatan(start, end);
        if (pendapatan == null) pendapatan = 0.0;
        
        long sudahBayar = transaksiRepository.findByStatus(StatusTransaksi.sudah_bayar).size();
        long belumBayar = transaksiRepository.findByStatus(StatusTransaksi.belum_bayar).size();
        long diberhentikan = transaksiRepository.findByStatus(StatusTransaksi.diberhentikan).size();
        
        Map<String, Object> laporan = new HashMap<>();
        laporan.put("periode", Map.of("start", start, "end", end));
        laporan.put("total_pendapatan", pendapatan);
        laporan.put("rincian_status", Map.of(
            "sudah_bayar", sudahBayar,
            "belum_bayar", belumBayar,
            "diberhentikan", diberhentikan
        ));
        
        return laporan;
    }
}