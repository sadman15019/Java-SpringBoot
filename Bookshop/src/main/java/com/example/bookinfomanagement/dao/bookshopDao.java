package com.example.bookinfomanagement.dao;

import com.example.bookinfomanagement.dto.bookshopDto;
import com.example.bookinfomanagement.entity.BookShop;
import com.example.bookinfomanagement.exception.deleteException;
import com.example.bookinfomanagement.repository.bookshopRepository;
import com.example.bookinfomanagement.service.helperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class bookshopDao {

    @Autowired
    private bookshopRepository bookshoprepository;

    public Optional<BookShop> getbyShopNo(String shopno)
    {
        return bookshoprepository.findByShopNo(shopno);
    }

    public BookShop save(BookShop bookshop)
    {
        return bookshoprepository.save(bookshop);
    }

    public List<BookShop> findAll() {
        return bookshoprepository.findAll();  /// findAll is a builtin funtion of JPArepository for viewing all the entries in database using ORM
    }

    @Transactional
    public void deletebyShopNo(String shopno) {
            bookshoprepository.deleteByShopNo(shopno);
    }


    public BookShop update(String shopNo, BookShop existingBook, BookShop updatedbook) {
            existingBook.setName(updatedbook.getName());
            existingBook.setShopNo(updatedbook.getShopNo());
            existingBook.setAddress(updatedbook.getAddress());
            existingBook.setHotLine(updatedbook.getHotLine());

            // Add other fields to update as necessary

            // Save the updated book (JpaRepository.save will update since the ID exists)
            return bookshoprepository.save(existingBook);
    }

}

