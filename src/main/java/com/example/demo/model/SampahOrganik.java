package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sampah_organik")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_jenis")
public class SampahOrganik extends JenisSampah {
    // Bisa tambah field khusus organik nanti
}