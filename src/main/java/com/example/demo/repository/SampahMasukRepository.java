package com.example.demo.repository;

import com.example.demo.model.JenisSampah;
import com.example.demo.model.SampahMasuk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SampahMasukRepository extends JpaRepository<SampahMasuk, Integer> {
    
    // Find by jenis sampah (FK)
    List<SampahMasuk> findByJenisSampah(JenisSampah jenisSampah);
    
    // Find by date range
    List<SampahMasuk> findByTanggalMasukBetween(LocalDateTime start, LocalDateTime end);
    
    // Find by KK
    List<SampahMasuk> findByNoKk(String noKk);
    
    // Total per jenis sampah (JOIN ke tabel jenis_sampah)
    @Query("SELECT sm.jenisSampah.namaJenis, SUM(sm.jumlahMasuk) FROM SampahMasuk sm " +
           "WHERE sm.tanggalMasuk BETWEEN :start AND :end GROUP BY sm.jenisSampah.namaJenis")
    List<Object[]> getTotalPerJenisSampah(@Param("start") LocalDateTime start, 
                                           @Param("end") LocalDateTime end);
    
    // Total semua sampah
    @Query("SELECT SUM(sm.jumlahMasuk) FROM SampahMasuk sm")
    Integer getTotalSemuaSampah();
}