package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Register Admin
    public Admin register(Admin admin){
        return adminRepository.save(admin);
    }

    // Login
    public Admin login(String username, String password){

        Admin admin = adminRepository.findByUsername(username);

        if(admin != null && admin.getPassword().equals(password)){
            return admin;
        }

        throw new RuntimeException("Invalid username or password");
    }

}