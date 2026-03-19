package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookRequest;
import com.example.demo.entity.IssueRecord;
import com.example.demo.entity.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookRequestRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookRequestService {

    @Autowired
    private BookRequestRepository repository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    // ===============================
    // Student sends request
    // ===============================
    public BookRequest requestBook(BookRequest request){

        request.setStatus("PENDING");
        request.setRequestDate(LocalDate.now());

        return repository.save(request);
    }

    // ===============================
    // Admin sees all requests
    // ===============================
    public List<BookRequest> getAllRequests(){
        return repository.findAll();
    }

    // ===============================
    // Deny request
    // ===============================
    public BookRequest denyRequest(Long id){

        BookRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if(!request.getStatus().equals("PENDING")){
            throw new RuntimeException("Request already processed");
        }

        request.setStatus("REJECTED");

        return repository.save(request);
    }

    // ===============================
    // ✅ FIXED APPROVE METHOD
    // ===============================
    public BookRequest approveRequest(Long id){

        BookRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // prevent re-approve
        if(!request.getStatus().equals("PENDING")){
            throw new RuntimeException("Request already processed");
        }

        // get student
        Student student = studentRepository.findByStudentName(request.getStudentName());

        if(student == null){
            throw new RuntimeException("Student not found");
        }

        // 🔥 FIX 1: CHECK DUPLICATE FIRST
        boolean alreadyIssued =
                issueRepository.existsByStudent_StudentNameAndBookIdAndReturnDateIsNull(
                        request.getStudentName(),
                        request.getBookId()
                );

        if(alreadyIssued){
            throw new RuntimeException("Student already has this book");
        }

        // get book
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 🔥 FIX 2: CHECK AVAILABILITY AFTER
        if(book.getQuantity() <= 0){
            throw new RuntimeException("Book not available");
        }

        // reduce quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // create issue record
        IssueRecord issue = new IssueRecord();
        issue.setStudent(student);
        issue.setBookId(book.getId());
        issue.setBookTitle(book.getTitle());
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(null);

        issueRepository.save(issue);

        request.setStatus("APPROVED");

        return repository.save(request);
    }
}