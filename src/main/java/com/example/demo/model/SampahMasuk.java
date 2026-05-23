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
    @JoinColumn(name = "kategori", referencedColumnName = "idJenis")
    private JenisSampah jenisSampah;
    
    private Integer jumlahMasuk;
    
    @Column(name = "no_kk")
    private String noKk;
    
    @Column(name = "tanggal_masuk")
    private LocalDateTime tanggalMasuk;
    
    // Getter dan Setter manual (karena Lombok mungkin bermasalah)
    public Integer getNoSampah() { return noSampah; }
    public void setNoSampah(Integer noSampah) { this.noSampah = noSampah; }
    
    public JenisSampah getJenisSampah() { return jenisSampah; }
    public void setJenisSampah(JenisSampah jenisSampah) { this.jenisSampah = jenisSampah; }
    
    public Integer getJumlahMasuk() { return jumlahMasuk; }
    public void setJumlahMasuk(Integer jumlahMasuk) { this.jumlahMasuk = jumlahMasuk; }
    
    public String getNoKk() { return noKk; }
    public void setNoKk(String noKk) { this.noKk = noKk; }
    
    public LocalDateTime getTanggalMasuk() { return tanggalMasuk; }
    public void setTanggalMasuk(LocalDateTime tanggalMasuk) { this.tanggalMasuk = tanggalMasuk; }
}