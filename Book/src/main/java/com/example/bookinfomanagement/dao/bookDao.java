package com.example.bookinfomanagement.dao;

import com.example.bookinfomanagement.entity.Book;
import com.example.bookinfomanagement.repository.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class bookDao {

    @Autowired
    private bookRepository bookrepository;

    public Optional<Book> findByIsbn(String isbn)
    {
        return bookrepository.findByIsbn(isbn);
    }

    public Book save(Book book)
    {
        return bookrepository.save(book);
    }

    public List<Book> findAll() {
        return bookrepository.findAll();  /// findAll is a builtin funtion of JPArepository for viewing all the entries in database using ORM
    }

    @Transactional
    public void deletebyIsbn(String isbn) {
            bookrepository.deleteByIsbn(isbn);
    }


    public Book update(String isbn, Book existingBook, Book updatedbook) {
            existingBook.setIsbn(updatedbook.getIsbn());
            existingBook.setName(updatedbook.getName());
            existingBook.setType(updatedbook.getType());
            existingBook.setAuthor(updatedbook.getAuthor());
            existingBook.setPrice(updatedbook.getPrice());

            // Add other fields to update as necessary

            // Save the updated book (JpaRepository.save will update since the ID exists)
            return bookrepository.save(existingBook);
    }

}

