package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StatusTransaksi;
import com.example.demo.model.TransaksiSampah;

@Repository
public interface TransaksiRepository extends JpaRepository<TransaksiSampah, String> {
    
    // Cari transaksi berdasarkan KK
    List<TransaksiSampah> findByNoKk(String noKk);
    
    // Cari transaksi berdasarkan status
    List<TransaksiSampah> findByStatus(StatusTransaksi status);
    
    // Cari transaksi berdasarkan KK dan status (misal: cek tunggakan)
    List<TransaksiSampah> findByNoKkAndStatus(String noKk, StatusTransaksi status);
    
    // Cek apakah keluarga punya tunggakan (belum bayar)
    boolean existsByNoKkAndStatus(String noKk, StatusTransaksi status);
    
    // Cari transaksi yang sudah bayar dalam range tanggal
    List<TransaksiSampah> findByStatusAndTanggalBayarBetween(StatusTransaksi status, 
                                                              LocalDateTime start, 
                                                              LocalDateTime end);
    
    // Hitung total transaksi per status
    @Query("SELECT t.status, COUNT(t) FROM TransaksiSampah t GROUP BY t.status")
    List<Object[]> countByStatus();
    
    // Hitung total pendapatan (asumsi per transaksi Rp 50.000)
    @Query("SELECT COUNT(t) * 50000 FROM TransaksiSampah t WHERE t.status = 'sudah_bayar' AND t.tanggalBayar BETWEEN :start AND :end")
    Long getTotalPendapatan(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}