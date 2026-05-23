package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaksi_sampah")
public class TransaksiSampah {
    
    @Id
    private String noTransaksi;
    
    @Column(name = "no_kk")
    private String noKk;
    
    @Column(name = "tanggal_bayar")
    private LocalDateTime tanggalBayar;
    
    @Enumerated(EnumType.STRING)
    private StatusTransaksi status;
    
    public TransaksiSampah() {
        String date = LocalDateTime.now().toString();
        this.noTransaksi = "TRX/" + System.currentTimeMillis();
        this.status = StatusTransaksi.belum_bayar;
    }
    
    // Getter Setter manual
    public String getNoTransaksi() { return noTransaksi; }
    public void setNoTransaksi(String noTransaksi) { this.noTransaksi = noTransaksi; }
    
    public String getNoKk() { return noKk; }
    public void setNoKk(String noKk) { this.noKk = noKk; }
    
    public LocalDateTime getTanggalBayar() { return tanggalBayar; }
    public void setTanggalBayar(LocalDateTime tanggalBayar) { this.tanggalBayar = tanggalBayar; }
    
    public StatusTransaksi getStatus() { return status; }
    public void setStatus(StatusTransaksi status) { this.status = status; }
}