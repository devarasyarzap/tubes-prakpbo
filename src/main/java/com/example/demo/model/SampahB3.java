package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sampah_b3")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_jenis")
public class SampahB3 extends JenisSampah {
    // Bisa tambah field khusus B3 nanti
}