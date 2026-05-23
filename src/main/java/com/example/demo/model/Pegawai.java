package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pegawai") // Membuat tabel terpisah bernama 'pegawai'
public class Pegawai extends User {

    // Kelas User kamu aman, tidak disentuh.
    // Tabel 'pegawai' di database akan punya kolom nama, email, kk, dll sendiri.

    public Pegawai() {
        super();
    }
}