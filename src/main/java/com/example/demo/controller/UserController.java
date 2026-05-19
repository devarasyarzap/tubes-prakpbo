package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;


@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", service.getAll());
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/save")
    public String save(User user) {
        service.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = service.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("users", service.getAll());
        return "index";
    }

    // Update - Proses update data
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, User user) {
        user.setId(id);
        service.save(user);
        return "redirect:/";
    }

    // Delete - Hapus data
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/";
    }
}