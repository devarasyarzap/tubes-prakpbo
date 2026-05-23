package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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