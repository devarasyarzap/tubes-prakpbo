package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pegawai;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {
    
    // Login: cari berdasarkan nama pegawai dan password
    Optional<Pegawai> findByNamaPegawaiAndPassword(String namaPegawai, String password);
    
    // Cek apakah nama pegawai sudah terdaftar
    boolean existsByNamaPegawai(String namaPegawai);
    
    // Cari berdasarkan nama saja
    Optional<Pegawai> findByNamaPegawai(String namaPegawai);
}