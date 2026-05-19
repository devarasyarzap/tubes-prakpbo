package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sampah_anorganik")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_jenis")
public class SampahAnorganik extends JenisSampah {
    // Bisa tambah field khusus anorganik nanti
}