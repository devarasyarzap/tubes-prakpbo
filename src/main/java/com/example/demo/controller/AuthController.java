package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.Keluarga;
import com.example.demo.model.Pegawai;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.KeluargaRepository;
import com.example.demo.repository.PegawaiRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private KeluargaRepository keluargaRepository;
    
    @Autowired
    private PegawaiRepository pegawaiRepository;
    
    @Autowired
    private AdminRepository adminRepository;

    // ========== REGISTER KELUARGA ==========
    @PostMapping("/register")
    public ResponseEntity<?> registerKeluarga(@RequestBody Keluarga keluarga) {
        System.out.println("Register attempt: email=" + keluarga.getEmail());

        if (keluargaRepository.findByEmail(keluarga.getEmail()).isPresent()) {
             Map<String, Object> error = new HashMap<>();
             error.put("success", false);
             error.put("message", "Email sudah terdaftar!");
             return ResponseEntity.badRequest().body(error);
        }

        // Simpan data keluarga baru ke database
        keluargaRepository.save(keluarga);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Registrasi berhasil");
        
        return ResponseEntity.ok(response);
    }

    // ========== LOGIN KELUARGA ==========
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        System.out.println("Login attempt (Keluarga): email=" + email);

        Optional<Keluarga> userOpt = keluargaRepository.findByEmailAndPassword(email, password);

        if (userOpt.isPresent()) {
            Keluarga user = userOpt.get();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login berhasil");
            
            Map<String, Object> data = new HashMap<>();
            data.put("noKK", user.getNoKk());
            data.put("namaKepalaKeluarga", user.getNamaKepala());
            data.put("jumlahAnggota", user.getJumlahAnggota());
            data.put("alamatDomisili", user.getAlamat());
            data.put("emailKontak", user.getEmail());
            data.put("noTelp", user.getNoTelp());
            data.put("role", user.getRole());
            
            response.put("data", data);

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Email atau password salah!");
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    // ========== LOGIN PEGAWAI ==========
    @PostMapping("/login/pegawai")
    public ResponseEntity<?> loginPegawai(@RequestBody Map<String, String> request) {
        String namaPegawai = request.get("nama");
        String password = request.get("password");

        System.out.println("Login attempt (Pegawai): nama=" + namaPegawai);

        Optional<Pegawai> pegawaiOpt = pegawaiRepository.findByNamaPegawaiAndPassword(namaPegawai, password);

        if (pegawaiOpt.isPresent()) {
            Pegawai pegawai = pegawaiOpt.get();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login berhasil");
            
            Map<String, Object> data = new HashMap<>();
            data.put("idPegawai", pegawai.getIdPegawai());
            data.put("namaPegawai", pegawai.getNamaPegawai());
            data.put("role", pegawai.getRole());
            
            response.put("data", data);

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Nama atau password salah!");
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    // ========== LOGIN ADMIN ==========
    @PostMapping("/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        System.out.println("Login attempt (Admin): email=" + email);

        Optional<Admin> adminOpt = adminRepository.findByEmailAndPassword(email, password);

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login berhasil");
            
            Map<String, Object> data = new HashMap<>();
            data.put("idAdmin", admin.getIdAdmin());
            data.put("namaAdmin", admin.getNamaAdmin());
            data.put("email", admin.getEmail());
            data.put("role", admin.getRole());
            
            response.put("data", data);

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Email atau password salah!");
            return ResponseEntity.badRequest().body(error);
        }
    }
}