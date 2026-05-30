package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jenis_sampah")
@Inheritance(strategy = InheritanceType.JOINED)
public class JenisSampah {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJenis;
    
    private String namaJenis;
    
    // No-arg constructor WAJIB ada
    public JenisSampah() {
    }
    
    public JenisSampah(Integer idJenis, String namaJenis) {
        this.idJenis = idJenis;
        this.namaJenis = namaJenis;
    }
    
    // Getter Setter
    public Integer getIdJenis() { return idJenis; }
    public void setIdJenis(Integer idJenis) { this.idJenis = idJenis; }
    
    public String getNamaJenis() { return namaJenis; }
    public void setNamaJenis(String namaJenis) { this.namaJenis = namaJenis; }
}