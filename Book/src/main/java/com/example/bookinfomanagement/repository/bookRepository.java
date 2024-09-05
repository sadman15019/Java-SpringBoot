package com.example.bookinfomanagement.repository;

import com.example.bookinfomanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface bookRepository extends JpaRepository <Book, Integer> {
    Optional<Book> findByIsbn( String isbn);  // Derived query method for getting a record from bookshop table using shopNo

    void deleteByIsbn(String isbn);  // Derived query method for deleting a record from bookshop table using shopNo
}
