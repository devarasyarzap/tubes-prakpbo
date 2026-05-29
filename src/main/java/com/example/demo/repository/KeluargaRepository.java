package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Keluarga;

@Repository
public interface KeluargaRepository extends JpaRepository<Keluarga, String> {
    
    // ========== BASIC CRUD (otomatis dari JpaRepository) ==========
    // findAll(), findById(), save(), delete(), dll sudah tersedia
    
    // ========== FIND BY FIELD ==========
    
    // Cari berdasarkan email
    Optional<Keluarga> findByEmail(String email);
    
    // Cari berdasarkan email dan password (untuk login)
    Optional<Keluarga> findByEmailAndPassword(String email, String password);
    
    // Cari berdasarkan nomor KK
    Optional<Keluarga> findByNoKk(String noKk);
    
    // Cari berdasarkan nama kepala keluarga (contains/partial match)
    List<Keluarga> findByNamaKepalaContainingIgnoreCase(String namaKepala);
    
    // Cari berdasarkan role (misal: semua Keluarga)
    List<Keluarga> findByRole(String role);
    
    // ========== CHECK EXISTS ==========
    
    // Cek apakah email sudah terdaftar
    boolean existsByEmail(String email);
    
    // Cek apakah nomor KK sudah terdaftar
    boolean existsByNoKk(String noKk);
    
    // ========== CUSTOM QUERY ==========
    
    // Cari keluarga dengan jumlah anggota lebih dari nilai tertentu
    @Query("SELECT k FROM Keluarga k WHERE k.jumlahAnggota >= :minAnggota")
    List<Keluarga> findKeluargaDenganAnggotaMinimal(@Param("minAnggota") int minAnggota);
    
    // Hitung total keluarga
    @Query("SELECT COUNT(k) FROM Keluarga k")
    long getTotalKeluarga();
    
    // Cari keluarga berdasarkan alamat (partial match)
    @Query("SELECT k FROM Keluarga k WHERE k.alamat LIKE %:alamat%")
    List<Keluarga> findByAlamatContaining(@Param("alamat") String alamat);
}