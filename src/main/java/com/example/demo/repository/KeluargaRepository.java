package com.example.demo.repository;

import com.example.demo.model.Keluarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeluargaRepository extends JpaRepository<Keluarga, String> {
}