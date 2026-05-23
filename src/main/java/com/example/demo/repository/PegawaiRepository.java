package com.example.demo.repository;

import com.example.demo.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, String> {
}