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

import com.example.demo.model.Keluarga;
import com.example.demo.repository.KeluargaRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private KeluargaRepository keluargaRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        System.out.println("Login attempt: email=" + email + ", password=" + password);

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
}