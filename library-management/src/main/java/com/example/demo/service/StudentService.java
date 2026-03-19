package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Register student
    public Student register(Student student){
        return studentRepository.save(student);
    }

    // Student login
    public Student login(String name,String password){

        Student student = studentRepository.findByStudentName(name);

        if(student != null && student.getPassword().equals(password)){
            return student;
        }

        throw new RuntimeException("Invalid student credentials");
    }

}