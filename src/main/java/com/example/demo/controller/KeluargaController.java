package com.example.demo.controller;

import com.example.demo.service.KeluargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KeluargaController {

    @Autowired
    private KeluargaService keluargaService;

    @GetMapping("/dashboard-keluarga")
    public String viewDashboardKeluarga(Model model) {
        model.addAttribute("daftarKeluarga", keluargaService.getAllKeluarga());
        return "dashboard"; // Ini mengarah ke dashboard.html kamu
    }
}