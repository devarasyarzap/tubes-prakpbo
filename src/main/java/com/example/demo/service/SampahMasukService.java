package com.example.demo.service;

import com.example.demo.model.SampahMasuk;
import com.example.demo.repository.SampahMasukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SampahMasukService {
    
    @Autowired
    private SampahMasukRepository sampahMasukRepository;
    
    // ========== CRUD ==========
    
    public List<SampahMasuk> getAll() {
        return sampahMasukRepository.findAll();
    }
    
    public SampahMasuk getById(Integer noSampah) {
        return sampahMasukRepository.findById(noSampah).orElse(null);
    }
    
    @Transactional
    public SampahMasuk create(SampahMasuk sampahMasuk) {
        return sampahMasukRepository.save(sampahMasuk);
    }
    
    @Transactional
    public SampahMasuk update(Integer noSampah, SampahMasuk updatedData) {
        SampahMasuk existing = getById(noSampah);
        if (existing != null) {
            existing.setJenisSampah(updatedData.getJenisSampah());
            existing.setJumlahMasuk(updatedData.getJumlahMasuk());
            existing.setNoKk(updatedData.getNoKk());
            existing.setTanggalMasuk(updatedData.getTanggalMasuk());
            return sampahMasukRepository.save(existing);
        }
        return null;
    }
    
    @Transactional
    public boolean delete(Integer noSampah) {
        if (sampahMasukRepository.existsById(noSampah)) {
            sampahMasukRepository.deleteById(noSampah);
            return true;
        }
        return false;
    }
    
    // ========== Query Khusus (Sesuai PDF & Class Diagram) ==========
    
    // /api/sampah-masuk/keluarga/{noKk}
    public List<SampahMasuk> getByKeluarga(String noKk) {
        return sampahMasukRepository.findByNoKk(noKk);
    }
    
    // /api/laporan/sampah?start=...&end=...
    public Map<String, Object> getLaporanSampah(LocalDateTime start, LocalDateTime end) {
        List<Object[]> results = sampahMasukRepository.getTotalPerJenisSampah(start, end);
        Map<String, Integer> perJenis = new HashMap<>();
        
        for (Object[] row : results) {
            String namaJenis = (String) row[0];
            Integer total = ((Number) row[1]).intValue();
            perJenis.put(namaJenis, total);
        }
        
        Map<String, Object> laporan = new HashMap<>();
        laporan.put("periode", Map.of("start", start, "end", end));
        laporan.put("per_jenis_sampah", perJenis);
        laporan.put("total_keseluruhan", sampahMasukRepository.getTotalSemuaSampah());
        
        return laporan;
    }
    
    // Hitung dampak lingkungan per keluarga (sesuai PDF: /api/laporan/sampah/{noKk})
    public Map<String, Object> getDampakLingkungan(String noKk) {
        List<SampahMasuk> sampahKeluarga = sampahMasukRepository.findByNoKk(noKk);
        
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
        
        Map<String, Object> dampak = new HashMap<>();
        dampak.put("no_kk", noKk);
        dampak.put("total_sampah", totalOrganik + totalAnorganik + totalB3);
        dampak.put("sampah_organik_kg", totalOrganik);
        dampak.put("sampah_anorganik_kg", totalAnorganik);
        dampak.put("sampah_b3_kg", totalB3);
        dampak.put("dampak_lingkungan", hitungDampak(totalOrganik, totalAnorganik, totalB3));
        
        return dampak;
    }
    
    private String hitungDampak(int organik, int anorganik, int b3) {
        double emisiCO2 = (organik * 0.5) + (anorganik * 2) + (b3 * 5);
        if (emisiCO2 < 50) return "Rendah - Kontribusi lingkungan baik";
        if (emisiCO2 < 150) return "Sedang - Perlu peningkatan kesadaran";
        return "Tinggi - Segera kurangi produksi sampah!";
    }
}