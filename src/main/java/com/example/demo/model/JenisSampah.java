package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jenis_sampah")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class JenisSampah {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJenis;
    
    @Column(length = 50, nullable = false, unique = true)
    private String namaJenis;
}