package com.example.bookinfomanagement.service;


import com.example.bookinfomanagement.dto.bookDto;
import com.example.bookinfomanagement.exception.deleteException;
import com.example.bookinfomanagement.dao.bookDao;
import com.example.bookinfomanagement.entity.Book;
import com.example.bookinfomanagement.repository.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class bookService {

    @Autowired
    private bookDao bookdao;

    @Autowired
    private helperService helperservice;


    public bookDto addBook(bookDto request) {
        Optional <Book> existingEntity= bookdao.findByIsbn(request.getIsbn()) ;  // ShopNo uniquley identify each shop
        if(existingEntity.isPresent())
        {
            throw new IllegalArgumentException("Bookshop slready exist with shopNo: "+request.getIsbn()); // If shopno already exists, thorow an exception
        }

        Book newEntity= helperservice.getBookEntityfromDto(request); // convert the request DTO to entity

        newEntity= bookdao.save(newEntity); // pass it to DAO for database update
        return helperservice.getDtofromBookEntity(newEntity); // return response as a form of DTO
    }

    public List<bookDto> getAll() {
        List<bookDto> result= new ArrayList<>();
        List<Book>books = bookdao.findAll();    /// get all the bookshop entities from database using DAO
        for (Book bs : books)
        {
            result.add(helperservice.getDtofromBookEntity(bs)); // convert the entities to dto
        }
        return result;
    }

    public bookDto getBook(String isbn) {
        Optional<Book> bookshop = bookdao.findByIsbn(isbn);

        if(bookshop.isEmpty())  // if the requested bookshop not found , throw an exception
        {
            throw new PropertyNotFoundException("bookshop not found with no: "+ isbn);
        }
        return helperservice.getDtofromBookEntity(bookshop.get()); // return the bookshop info response as DTO

    }

    public void deleteBook(String isbn) {
        Optional<Book> bookshop = bookdao.findByIsbn(isbn);

        if(bookshop.isEmpty())  // if the requested bookshop not found , throw an exception
        {
            throw new deleteException("BookShop with no " + isbn + " not found, deletion failed.");
        }

        bookdao.deletebyIsbn(isbn);
    }

    public bookDto updateBook(String isbn, bookDto updatedbook) {

        Optional<Book> book = bookdao.findByIsbn(isbn);

        if(book.isEmpty())  // if the requested bookshop not found , throw an exception
        {
            throw new deleteException("BookShop with no " + isbn + " not found, updation failed.");
        }
        return helperservice.getDtofromBookEntity(bookdao.update(isbn, book.get(), helperservice.getBookEntityfromDto(updatedbook)));  // retrieve the entity from databse, update the bookshop using builder function and return it after converting to DTO
    }

}
