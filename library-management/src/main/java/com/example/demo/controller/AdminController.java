package com.example.demo.controller;

import com.example.demo.entity.Admin;

import com.example.demo.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
//@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Register Admin
    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin){
        return adminService.register(admin);
    }

    // Admin Login
    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin){
        return adminService.login(admin.getUsername(), admin.getPassword());
    }

}