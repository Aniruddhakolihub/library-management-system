package com.example.demo.repository;

import com.example.demo.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

// Search exact title
List<Book> findByTitle(String title);

// Search title partially (case insensitive)
List<Book> findByTitleContainingIgnoreCase(String title);



// Find book by id
Optional<Book> findById(Long id);



}
