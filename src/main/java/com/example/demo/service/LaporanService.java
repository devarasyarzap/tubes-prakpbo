package com.example.demo.service;

import com.example.demo.dto.LaporanDTO;
import com.example.demo.model.SampahMasuk;
import com.example.demo.model.TransaksiSampah;
import com.example.demo.repository.SampahMasukRepository;
import com.example.demo.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LaporanService {
    
    @Autowired
    private SampahMasukRepository sampahMasukRepository;
    
    @Autowired
    private TransaksiRepository transaksiRepository;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // ========== LAPORAN SESUAI PDF ==========
    
    // GET /api/laporan/sampah - Tampilkan laporan total sampah (admin)
    public LaporanDTO getLaporanSampah(LocalDateTime start, LocalDateTime end) {
        List<SampahMasuk> sampahList = sampahMasukRepository.findByTanggalMasukBetween(start, end);
        
        // Hitung per jenis sampah
        Map<String, Integer> perJenis = new HashMap<>();
        int total = 0;
        
        for (SampahMasuk sm : sampahList) {
            String namaJenis = sm.getJenisSampah().getNamaJenis();
            perJenis.put(namaJenis, perJenis.getOrDefault(namaJenis, 0) + sm.getJumlahMasuk());
            total += sm.getJumlahMasuk();
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("per_jenis_sampah", perJenis);
        data.put("total_keseluruhan_kg", total);
        data.put("total_transaksi_sampah", sampahList.size());
        data.put("detail", sampahList.stream().limit(10).toList()); // 10 data terakhir
        
        return new LaporanDTO(
            "Laporan Total Sampah",
            start,
            end,
            data,
            LocalDateTime.now().format(formatter)
        );
    }
    
    // GET /api/laporan/sampah/{noKk} - Hitung dampak lingkungan per keluarga
    public LaporanDTO getDampakLingkunganPerKeluarga(String noKk, LocalDateTime start, LocalDateTime end) {
        List<SampahMasuk> sampahKeluarga = sampahMasukRepository.findByNoKk(noKk);
        
        // Filter by date range jika ada
        if (start != null && end != null) {
            sampahKeluarga = sampahKeluarga.stream()
                .filter(sm -> sm.getTanggalMasuk().isAfter(start) && sm.getTanggalMasuk().isBefore(end))
                .toList();
        }
        
        int totalOrganik = 0;
        int totalAnorganik = 0;
        int totalB3 = 0;
        
        for (SampahMasuk sm : sampahKeluarga) {
            String namaJenis = sm.getJenisSampah().getNamaJenis().toLowerCase();
            if (namaJenis.contains("organik")) {
                totalOrganik += sm.getJumlahMasuk();
            } else if (namaJenis.contains("anorganik")) {
                totalAnorganik += sm.getJumlahMasuk();
            } else if (namaJenis.contains("b3")) {
                totalB3 += sm.getJumlahMasuk();
            }
        }
        
        double emisiCO2 = (totalOrganik * 0.5) + (totalAnorganik * 2) + (totalB3 * 5);
        String tingkatDampak;
        String rekomendasi;
        
        if (emisiCO2 < 50) {
            tingkatDampak = "Rendah";
            rekomendasi = "Pertahankan kebiasaan baik!";
        } else if (emisiCO2 < 150) {
            tingkatDampak = "Sedang";
            rekomendasi = "Mulai kurangi plastik sekali pakai dan daur ulang sampah anorganik.";
        } else {
            tingkatDampak = "Tinggi";
            rekomendasi = "Segera kurangi produksi sampah! Beralih ke produk ramah lingkungan.";
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("no_kk", noKk);
        data.put("total_sampah_kg", totalOrganik + totalAnorganik + totalB3);
        data.put("sampah_organik_kg", totalOrganik);
        data.put("sampah_anorganik_kg", totalAnorganik);
        data.put("sampah_b3_kg", totalB3);
        data.put("estimasi_emisi_co2_kg", emisiCO2);
        data.put("tingkat_dampak", tingkatDampak);
        data.put("rekomendasi", rekomendasi);
        
        return new LaporanDTO(
            "Dampak Lingkungan Keluarga",
            start != null ? start : LocalDateTime.MIN,
            end != null ? end : LocalDateTime.MAX,
            data,
            LocalDateTime.now().format(formatter)
        );
    }
    
    // ========== LAPORAN TAMBAHAN (Opsional) ==========
    
    // Laporan transaksi keuangan
    public LaporanDTO getLaporanKeuangan(LocalDateTime start, LocalDateTime end) {
        List<TransaksiSampah> transaksiList = transaksiRepository.findByTanggalBayarBetween(start, end);
        
        double totalPendapatan = 0;
        int sudahBayar = 0;
        int belumBayar = 0;
        int diberhentikan = 0;
        
        for (TransaksiSampah ts : transaksiList) {
            switch (ts.getStatus()) {
                case sudah_bayar:
                    sudahBayar++;
                    // Asumsi biaya per transaksi Rp 50.000
                    totalPendapatan += 50000;
                    break;
                case belum_bayar:
                    belumBayar++;
                    break;
                case diberhentikan:
                    diberhentikan++;
                    break;
            }
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("total_pendapatan", totalPendapatan);
        data.put("rincian_status", Map.of(
            "sudah_bayar", sudahBayar,
            "belum_bayar", belumBayar,
            "diberhentikan", diberhentikan
        ));
        data.put("total_transaksi", transaksiList.size());
        data.put("potensi_pendapatan", (belumBayar * 50000));
        
        return new LaporanDTO(
            "Laporan Keuangan",
            start,
            end,
            data,
            LocalDateTime.now().format(formatter)
        );
    }
    
    // Laporan per wilayah (berdasarkan KK)
    public LaporanDTO getLaporanPerWilayah(LocalDateTime start, LocalDateTime end) {
        List<SampahMasuk> sampahList = sampahMasukRepository.findByTanggalMasukBetween(start, end);
        
        Map<String, Integer> perKK = new HashMap<>();
        for (SampahMasuk sm : sampahList) {
            perKK.put(sm.getNoKk(), perKK.getOrDefault(sm.getNoKk(), 0) + sm.getJumlahMasuk());
        }
        
        // Urutkan berdasarkan yang paling banyak sampahnya
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(perKK.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        Map<String, Object> data = new HashMap<>();
        data.put("per_kk", perKK);
        data.put("top_3_produsen_sampah", sorted.stream().limit(3).toList());
        data.put("total_kk_aktif", perKK.size());
        
        return new LaporanDTO(
            "Laporan Per Wilayah",
            start,
            end,
            data,
            LocalDateTime.now().format(formatter)
        );
    }
    
    // Laporan tren sampah (harian/mingguan/bulanan)
    public LaporanDTO getLaporanTren(LocalDateTime start, LocalDateTime end, String periode) {
        List<SampahMasuk> sampahList = sampahMasukRepository.findByTanggalMasukBetween(start, end);
        
        Map<String, Integer> tren = new LinkedHashMap<>();
        
        switch (periode.toLowerCase()) {
            case "harian":
                sampahList.forEach(sm -> {
                    String key = sm.getTanggalMasuk().toLocalDate().toString();
                    tren.put(key, tren.getOrDefault(key, 0) + sm.getJumlahMasuk());
                });
                break;
            case "mingguan":
                sampahList.forEach(sm -> {
                    int week = sm.getTanggalMasuk().toLocalDate().get(java.time.temporal.WeekFields.ISO.weekOfWeekBasedYear());
                    String key = "Minggu ke-" + week;
                    tren.put(key, tren.getOrDefault(key, 0) + sm.getJumlahMasuk());
                });
                break;
            case "bulanan":
                sampahList.forEach(sm -> {
                    String key = sm.getTanggalMasuk().getYear() + "-" + sm.getTanggalMasuk().getMonthValue();
                    tren.put(key, tren.getOrDefault(key, 0) + sm.getJumlahMasuk());
                });
                break;
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("periode", periode);
        data.put("tren", tren);
        data.put("rata_rata", tren.values().stream().mapToInt(Integer::intValue).average().orElse(0));
        
        return new LaporanDTO(
            "Laporan Tren Sampah",
            start,
            end,
            data,
            LocalDateTime.now().format(formatter)
        );
    }
}