package com.example.demo.controller;

import com.example.demo.entity.IssueRecord;
import com.example.demo.service.IssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
//@CrossOrigin(origins = "*")   // allows frontend pages to call APIs
public class IssueController {

    @Autowired
    private IssueService issueService;

    // =====================================
    // Issue a Book
    // =====================================
    @PostMapping("/book")
    public IssueRecord issueBook(@RequestBody IssueRecord record) {
        return issueService.issueBook(record);
    }

    // =====================================
    // Get All Issued Books (History)
    // =====================================
    @GetMapping("/history")
    public List<IssueRecord> history() {
        return issueService.getAllIssuedBooks();
    }

    // =====================================
    // Get Issue Record By ID
    // =====================================
    @GetMapping("/{id}")
    public IssueRecord getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    // =====================================
    // Return Book
    // =====================================
    @PutMapping("/return/{id}")
    public IssueRecord returnBook(@PathVariable Long id) {
        return issueService.returnBook(id);
    }

    // =====================================
    // Delete Issue Record
    // =====================================
    @DeleteMapping("/delete/{id}")
    public void deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
    }

}