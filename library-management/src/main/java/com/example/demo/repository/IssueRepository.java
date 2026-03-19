package com.example.demo.repository;

import com.example.demo.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<IssueRecord, Long> {

    List<IssueRecord> findByStudent_StudentName(String studentName);

    long countByReturnDateIsNull();

    boolean existsByStudent_StudentNameAndBookIdAndReturnDateIsNull(String studentName, Long bookId);

}