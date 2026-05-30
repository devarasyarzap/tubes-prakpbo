package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sampah_masuk")
public class SampahMasuk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noSampah;
    
    @ManyToOne
    @JoinColumn(name = "kategori", referencedColumnName = "idJenis")
    private JenisSampah jenisSampah;
    
    @Transient  // Tidak disimpan ke database, hanya untuk menerima input dari frontend
    private Integer jenisSampahId;
    
    private Integer jumlahMasuk;
    
    @Column(name = "no_kk")
    private String noKk;
    
    @Column(name = "tanggal_masuk")
    private LocalDateTime tanggalMasuk;
    
    // Setter untuk jenisSampahId - otomatis mengisi jenisSampah
    public void setJenisSampahId(Integer id) {
        this.jenisSampahId = id;
        if (id != null) {
            this.jenisSampah = new JenisSampah() {
                // Anonymous class - atau bisa pakai repository untuk mengambil dari DB
            };
            this.jenisSampah.setIdJenis(id);
        }
    }
    
    // Getter/Setter lainnya...
    public Integer getNoSampah() { return noSampah; }
    public void setNoSampah(Integer noSampah) { this.noSampah = noSampah; }
    
    public JenisSampah getJenisSampah() { return jenisSampah; }
    public void setJenisSampah(JenisSampah jenisSampah) { this.jenisSampah = jenisSampah; }
    
    public Integer getJenisSampahId() { return jenisSampahId; }
    
    public Integer getJumlahMasuk() { return jumlahMasuk; }
    public void setJumlahMasuk(Integer jumlahMasuk) { this.jumlahMasuk = jumlahMasuk; }
    
    public String getNoKk() { return noKk; }
    public void setNoKk(String noKk) { this.noKk = noKk; }
    
    public LocalDateTime getTanggalMasuk() { return tanggalMasuk; }
    public void setTanggalMasuk(LocalDateTime tanggalMasuk) { this.tanggalMasuk = tanggalMasuk; }
}