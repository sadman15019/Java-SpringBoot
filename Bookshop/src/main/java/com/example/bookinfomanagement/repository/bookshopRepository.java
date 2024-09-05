package com.example.bookinfomanagement.repository;

import com.example.bookinfomanagement.entity.BookShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface bookshopRepository extends JpaRepository <BookShop, Integer> {

    Optional <BookShop> findByShopNo(String shopNo);  // Derived query method for getting a record from bookshop table using shopNo

    void deleteByShopNo(String shopNo);  // Derived query method for deleting a record from bookshop table using shopNo
}
