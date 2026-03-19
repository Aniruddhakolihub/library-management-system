package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add Book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Delete Book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Smart Search (case insensitive + partial match)
    public List<Book> searchBook(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

}