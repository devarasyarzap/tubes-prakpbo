package com.example.demo.repository;

import com.example.demo.model.SampahMasuk;
import com.example.demo.model.TransaksiSampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface LaporanRepository {
    // Interface ini bisa extends atau dibuat terpisah
    // Tapi karena butuh query dari beberapa repository, kita buat class terpisah
}