package com.example.demo.repository;

import com.example.demo.entity.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRequestRepository extends JpaRepository<BookRequest,Long>{

    List<BookRequest> findByStudentName(String studentName);
    List<BookRequest> findByStudentNameAndBookId(String studentName, Long bookId);

}