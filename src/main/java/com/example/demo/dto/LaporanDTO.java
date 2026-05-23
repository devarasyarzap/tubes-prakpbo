package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaporanDTO {
    private String jenisLaporan;
    private LocalDateTime periodeStart;
    private LocalDateTime periodeEnd;
    private Map<String, Object> data;
    private String generatedAt;
}