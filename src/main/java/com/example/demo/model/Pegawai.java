package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pegawai")
public class Pegawai {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPegawai;
    
    @Column(name = "nama_pegawai", nullable = false)
    private String namaPegawai;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;
    
    // Constructor
    public Pegawai() {}
    
    public Pegawai(String namaPegawai, String password, String role) {
        this.namaPegawai = namaPegawai;
        this.password = password;
        this.role = role;
    }
    
    // Getter
    public Long getIdPegawai() { return idPegawai; }
    public String getNamaPegawai() { return namaPegawai; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    
    // Setter
    public void setIdPegawai(Long idPegawai) { this.idPegawai = idPegawai; }
    public void setNamaPegawai(String namaPegawai) { this.namaPegawai = namaPegawai; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}