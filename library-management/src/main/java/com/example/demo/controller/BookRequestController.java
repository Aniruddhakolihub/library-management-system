package com.example.demo.controller;

import com.example.demo.entity.BookRequest;
import com.example.demo.service.BookRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-request")
//@CrossOrigin("*")
public class BookRequestController {

    @Autowired
    private BookRequestService service;

    // ===============================
    // Student request
    // ===============================
    @PostMapping("/book")
    public BookRequest requestBook(@RequestBody BookRequest request){
        return service.requestBook(request);
    }

    // ===============================
    // Get all requests
    // ===============================
    @GetMapping("/all")
    public List<BookRequest> getAllRequests(){
        return service.getAllRequests();
    }

    // ===============================
    // ✅ FIXED APPROVE METHOD
    // ===============================
    @PutMapping("/approve/{id}")
    public Object approve(@PathVariable Long id){
        try {
            return service.approveRequest(id);
        } catch (RuntimeException e) {
            return e.getMessage(); // 🔥 RETURN CLEAN MESSAGE
        }
    }

    // ===============================
    // DENY
    // ===============================
    @PutMapping("/deny/{id}")
    public Object deny(@PathVariable Long id){
        try {
            return service.denyRequest(id);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}