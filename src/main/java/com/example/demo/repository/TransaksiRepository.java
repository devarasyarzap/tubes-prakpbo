package com.example.demo.repository;

import com.example.demo.model.StatusTransaksi;
import com.example.demo.model.TransaksiSampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<TransaksiSampah, String> {
    
    List<TransaksiSampah> findByNoKk(String noKk);
    List<TransaksiSampah> findByStatus(StatusTransaksi status);
}