package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
//@CrossOrigin(origins="*")
public class BookController {

    @Autowired
    private BookService bookService;

    // ===============================
    // Add Book
    // ===============================
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // ===============================
    // Get All Books
    // ===============================
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // ===============================
    // Delete Book
    // ===============================
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book Deleted Successfully";
    }

    // ===============================
    // Search Book
    // ===============================
    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam String title) {
        return bookService.searchBook(title);
    }

}