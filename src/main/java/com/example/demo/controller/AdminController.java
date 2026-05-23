package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// You imported User here
import com.example.demo.model.User; 
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserRepository adminRepository;

    // 1. Changed Pegawai to User here
    @PostMapping("/register")
    public User register(@RequestBody User admin) { 
        return adminRepository.save(admin);
    }

    // 2. Changed admin to User here
    @GetMapping
    public java.util.List<User> getAllUsers() { 
        return adminRepository.findAll();
    }
}