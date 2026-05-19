package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sampah_masuk")
public class SampahMasuk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noSampah;
    
    @ManyToOne
    @JoinColumn(name = "kategori", referencedColumnName = "idJenis", nullable = false)
    private JenisSampah jenisSampah;
    
    @Column(nullable = false)
    private Integer jumlahMasuk;
    
    @Column(name = "no_kk", nullable = false, length = 15)
    private String noKk;
    
    @Column(name = "tanggal_masuk", nullable = false)
    private LocalDateTime tanggalMasuk;
    
    @PrePersist
    protected void onCreate() {
        if (tanggalMasuk == null) {
            tanggalMasuk = LocalDateTime.now();
        }
    }
}