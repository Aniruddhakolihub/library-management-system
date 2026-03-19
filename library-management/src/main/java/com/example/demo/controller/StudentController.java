package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.IssueRecord;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
//@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private IssueRepository issueRepository;

    // =========================
    // Register Student
    // =========================
    @PostMapping("/register")
    public Student register(@RequestBody Student student){
        return studentService.register(student);
    }

    // =========================
    // Login Student
    // =========================
    @PostMapping("/login")
    public Student login(@RequestBody Student student){
        return studentService.login(student.getStudentName(), student.getPassword());
    }

    // =========================
    // Get My Issued Books
    // =========================
    @GetMapping("/my-books/{studentName}")
    public List<IssueRecord> getMyBooks(@PathVariable String studentName){
        return issueRepository.findByStudent_StudentName(studentName);
    }

}