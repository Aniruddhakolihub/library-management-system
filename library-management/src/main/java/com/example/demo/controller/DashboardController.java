package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
//@CrossOrigin(origins="*")
public class DashboardController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/stats")
    public Map<String, Long> getStats(){

        Map<String, Long> stats = new HashMap<>();

        long totalBooks = bookRepository.count();
        long issuedBooks = issueRepository.countByReturnDateIsNull();
        long totalStudents = studentRepository.count();
        long availableBooks = Math.max(totalBooks - issuedBooks, 0);

        stats.put("totalBooks", totalBooks);
        stats.put("issuedBooks", issuedBooks);
        stats.put("totalStudents", totalStudents);
        stats.put("availableBooks", availableBooks);

        return stats;
    }
}