package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaksi_sampah")
public class TransaksiSampah {
    
    @Id
    @Column(name = "no_transaksi", nullable = false, unique = true, length = 30)
    private String noTransaksi;
    
    @Column(name = "no_kk", nullable = false, length = 20)
    private String noKk;
    
    @Column(name = "tanggal_bayar")
    private LocalDateTime tanggalBayar;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransaksi status;
    
    public TransaksiSampah() {
        String date = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        String random = String.valueOf((int)(Math.random() * 10000));
        this.noTransaksi = "TRX/" + date + "/" + random;
        this.status = StatusTransaksi.belum_bayar;
    }
    
    public void bayar() {
        this.status = StatusTransaksi.sudah_bayar;
        this.tanggalBayar = LocalDateTime.now();
    }
    
    public void batalkan() {
        this.status = StatusTransaksi.diberhentikan;
    }
}

// ✅ INI YANG PENTING - tambahkan PUBLIC
public enum StatusTransaksi {
    belum_bayar,    // Masih menunggu pembayaran
    sudah_bayar,    // Sudah dibayar lunas
    diberhentikan   // Transaksi dibatalkan/dihentikan
}