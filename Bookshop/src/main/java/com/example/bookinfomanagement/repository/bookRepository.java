package com.example.bookinfomanagement.repository;

import com.example.bookinfomanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository <Book, Integer> {
}
