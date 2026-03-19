package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.IssueRecord;
import com.example.demo.entity.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private BookRepository bookRepository;

    // =====================================
    // Issue Book
    // =====================================
    public IssueRecord issueBook(IssueRecord record){

        if(record.getStudent() == null || record.getStudent().getStudentName() == null){
            throw new RuntimeException("Student name is required");
        }

        if(record.getBookId() == null){
            throw new RuntimeException("Book ID is required");
        }

        // 1. Get Book
        Book book = bookRepository.findById(record.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 2. Check quantity
        if(book.getQuantity() <= 0){
            throw new RuntimeException("Book is currently not available");
        }

        // 3. Get Student
        Student student = studentRepository.findByStudentName(
                record.getStudent().getStudentName()
        );

        if(student == null){
            student = new Student();
            student.setStudentName(record.getStudent().getStudentName());
            student.setBooksTaken(0);
        }

        // 4. Update student count
        student.setBooksTaken(student.getBooksTaken() + 1);
        studentRepository.save(student);

        // 5. Reduce book quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // 6. 🔥 IMPORTANT (SET DATA)
        record.setStudent(student);
        record.setBookTitle(book.getTitle());
        record.setIssueDate(LocalDate.now());
        record.setReturnDate(null); // VERY IMPORTANT

        // 7. 🔥 SAVE ISSUE RECORD
        return issueRepository.save(record);
    }

    // =====================================
    // Get All Issued Books
    // =====================================
    public List<IssueRecord> getAllIssuedBooks(){
        return issueRepository.findAll();
    }

    // =====================================
    // Return Book
    // =====================================
    public IssueRecord returnBook(Long id){

        IssueRecord record = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        if(record.getReturnDate() != null){
            throw new RuntimeException("Book already returned");
        }

        record.setReturnDate(LocalDate.now());

        Book book = bookRepository.findById(record.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        Student student = record.getStudent();

        if(student != null && student.getBooksTaken() > 0){
            student.setBooksTaken(student.getBooksTaken() - 1);
            studentRepository.save(student);
        }

        return issueRepository.save(record);
    }
    
 // =====================================
 // Get Issue By ID (FIX)
 // =====================================
 public IssueRecord getIssueById(Long id){

     return issueRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Issue not found"));
 }

    // =====================================
    // Delete Issue Record
    // =====================================
    public void deleteIssue(Long id){
        issueRepository.deleteById(id);
    }
}