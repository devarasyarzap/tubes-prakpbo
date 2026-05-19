package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "keluarga")
public class Keluarga {

    @Id
    @Column(name = "no_kk", length = 50)
    private String noKk;

    @Column(name = "nama_kepala")
    private String namaKepala;

    @Column(name = "jumlah_anggota")
    private Integer jumlahAnggota;

    private String alamat;

    @Column(name = "no_telp")
    private String noTelp;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    private String email;
    private String password;
    private String role;

    // --- CONSTRUCTOR ---
    public Keluarga() {}

    public Keluarga(String noKk, String namaKepala, Integer jumlahAnggota, String alamat, String noTelp, LocalDate tanggalLahir, String email, String password, String role) {
        this.noKk = noKk;
        this.namaKepala = namaKepala;
        this.jumlahAnggota = jumlahAnggota;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // --- GETTER & SETTER ---
    public String getNoKk() { return noKk; }
    public void setNoKk(String noKk) { this.noKk = noKk; }

    public String getNamaKepala() { return namaKepala; }
    public void setNamaKepala(String namaKepala) { this.namaKepala = namaKepala; }

    public Integer getJumlahAnggota() { return jumlahAnggota; }
    public void setJumlahAnggota(Integer jumlahAnggota) { this.jumlahAnggota = jumlahAnggota; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }

    public LocalDate getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(LocalDate tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}