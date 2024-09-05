package com.example.bookinfomanagement.service;


import com.example.bookinfomanagement.dto.bookDto2;
import com.example.bookinfomanagement.dto.bookshopDto;
import com.example.bookinfomanagement.dao.bookshopDao;
import com.example.bookinfomanagement.dto.bookshopDto2;
import com.example.bookinfomanagement.entity.BookShop;
import com.example.bookinfomanagement.exception.deleteException;
import com.example.bookinfomanagement.entity.Book;
import com.example.bookinfomanagement.repository.bookRepository;
import com.example.bookinfomanagement.repository.bookshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class bookshopService {

    @Autowired
    private bookshopDao bookshopdao;

    @Autowired
    private helperService helperservice;

    @Autowired
    private externalapiService externalapiservice;

    // captures request which is DTO, return response which is also DTO, in service DTO is converted to entity, performs business logic on entity, and then pass it to DTO for further operation.

    public bookshopDto addBookShop(bookshopDto request) {
        Optional <BookShop> existingEntity= bookshopdao.getbyShopNo(request.getShopNo()) ;  // ShopNo uniquley identify each shop
        if(existingEntity.isPresent())
        {
            throw new IllegalArgumentException("Bookshop slready exist with shopNo: "+request.getShopNo()); // If shopno already exists, thorow an exception
        }

        BookShop newEntity= helperservice.getBookShopEntityfromDTO(request); // convert the request DTO to entity

        newEntity= bookshopdao.save(newEntity); // pass it to DAO for database update
        return helperservice.getDTOfromBookShopEntity(newEntity); // return response as a form of DTO
    }

    /*
      1: get all bookshop entities from dao layer
      2: convert entities to dto and save it to result
      3: return result
    */
    public List<bookshopDto> getAll() {
         List<bookshopDto> result= new ArrayList<>();
         List<BookShop>bookshops = bookshopdao.findAll();    /// get all the bookshop entities from database using DAO
         for (BookShop bs : bookshops)
         {
             result.add(helperservice.getDTOfromBookShopEntity(bs)); // convert the entities to dto
         }
         return result;
    }

    // here for given bookshop, details of all the books are also needed using external api service
    // bookDto object has only isbn and discount rate
    // bookDto2 object has the book details

    public bookshopDto2 getBookShop(String shopNo) {
        Optional<BookShop> bookshop = bookshopdao.getbyShopNo(shopNo);
        if(bookshop.isEmpty())  // if the requested bookshop not found , throw an exception
        {
            throw new PropertyNotFoundException("bookshop not found with no: "+ shopNo);
        }
        List<Book> booklist= bookshop.get().getBooks();  // bookshop entity has a list of book entity having isbn and discount rate
        List<bookDto2> bookdtos2= externalapiservice.getbookDetails(booklist);
        return helperservice.getDTO2fromBookShopEntity(bookshop.get(),bookdtos2); // return the bookshop info response as DTO

    }

    public void deleteBookShop(String shopNo) {
        Optional<BookShop> bookshop = bookshopdao.getbyShopNo(shopNo);

        if(bookshop.isEmpty())  // if the requested bookshop not found , throw an exception
        {
            throw new deleteException("BookShop with no " + shopNo + " not found, deletion failed.");
        }

        bookshopdao.deletebyShopNo(shopNo);
    }

    public bookshopDto updateBookShop(String shopNo, bookshopDto updatedbook) {

        Optional<BookShop> bookshop = bookshopdao.getbyShopNo(shopNo);

        if(bookshop.isEmpty())  // if the requested bookshop not found , throw an exception
        {
            throw new deleteException("BookShop with no " + shopNo + " not found, updation failed.");
        }
        return helperservice.getDTOfromBookShopEntity(bookshopdao.update(shopNo, bookshop.get(), helperservice.getBookShopEntityfromDTO(updatedbook)));  // retrieve the entity from databse, update the bookshop using builder function and return it after converting to DTO
    }
}
