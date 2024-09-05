package com.example.bookinfomanagement.service;

import com.example.bookinfomanagement.dto.bookDto;
import com.example.bookinfomanagement.dto.bookDto2;
import com.example.bookinfomanagement.dto.bookshopDto;
import com.example.bookinfomanagement.dto.bookshopDto2;
import com.example.bookinfomanagement.entity.Book;
import com.example.bookinfomanagement.entity.BookShop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class helperService {

    public BookShop getBookShopEntityfromDTO(bookshopDto request)  // method for converting BooksHop DTO to Bookshop entity
    {
        BookShop bookshopEntity=BookShop.builder().name(request.getName()).
                shopNo(request.getShopNo()).address(request.getAddress()).hotLine(request.getHotLine()).build();  // initialize an object of bookshop using builder

        List<Book> bookEntities= getBookEntitiesfromDto(bookshopEntity, request.getBooks());  //converting the list of bookdto to list of book entity

        bookshopEntity.setBooks(bookEntities); // initialize the objects Books propoerty with this list

        return bookshopEntity;
    }
    public List<Book> getBookEntitiesfromDto(BookShop bookshopEntity, List<bookDto> bookdtos)  // parameter BookShop entity is required because Book entity has a BookShop reference
    {
        List<Book> bookEntities= new ArrayList<>();
        for (bookDto bookdto: bookdtos)
        {
            Book bookEntity= getBookEntityfromDto(bookshopEntity, bookdto);  // for each bookdDto in the bookList of the request , convert it to the bok entity
            bookEntities.add(bookEntity);
        }
        return bookEntities;
    }

    public Book getBookEntityfromDto(BookShop bookshopentity, bookDto bookdto)
    {
        return Book.builder().isbn(bookdto.getIsbn()).discount_rate(bookdto.getDiscount_rate()).bookshop(bookshopentity).build();
    }
    public bookshopDto getDTOfromBookShopEntity(BookShop bookshop)
    {
         bookshopDto bookshopdto= bookshopDto.builder().name(bookshop.getName()).shopNo(bookshop.getShopNo()).address(bookshop.getAddress()).hotLine(bookshop.getHotLine()).build();
         List<bookDto> bookdtos= getDtofromBookEntities(bookshopdto, bookshop.getBooks());
         bookshopdto.setBooks(bookdtos);
         return bookshopdto;
    }


    public bookshopDto2 getDTO2fromBookShopEntity(BookShop bookshop, List<bookDto2> bookdtos2 )
    {
        bookshopDto2 bookshopdto2= bookshopDto2.builder().name(bookshop.getName()).shopNo(bookshop.getShopNo()).address(bookshop.getAddress()).hotLine(bookshop.getHotLine()).build();
        bookshopdto2.setBooks(bookdtos2);
        return bookshopdto2;
    }

    public List<bookDto> getDtofromBookEntities(bookshopDto bookshopdto, List<Book> books)
    {
        List<bookDto> bookdtos= new ArrayList<>();
        for(Book book: books)
        {
            bookdtos.add(getDtofromBookEntity(bookshopdto, book));
        }
        return bookdtos;
    }
    public bookDto getDtofromBookEntity(bookshopDto bookshopdto, Book book)
    {
        return bookDto.builder().isbn(book.getIsbn()).discount_rate(book.getDiscount_rate()).build();
    }
}
