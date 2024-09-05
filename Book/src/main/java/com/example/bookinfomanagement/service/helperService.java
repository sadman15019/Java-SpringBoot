package com.example.bookinfomanagement.service;

import com.example.bookinfomanagement.dto.bookDto;
import com.example.bookinfomanagement.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class helperService {


    public List<Book> getBookEntitiesfromDto( List<bookDto> bookdtos)  // parameter BookShop entity is required because Book entity has a BookShop reference
    {
        List<Book> bookEntities= new ArrayList<>();
        for (bookDto bookdto: bookdtos)
        {
            Book bookEntity= getBookEntityfromDto(bookdto);  // for each bookdDto in the bookList of the request , convert it to the bok entity
            bookEntities.add(bookEntity);
        }
        return bookEntities;
    }

    public Book getBookEntityfromDto(bookDto bookdto)
    {
        return Book.builder().isbn(bookdto.getIsbn()).name(bookdto.getName()).type(bookdto.getType()).author(bookdto.getAuthor()).price(bookdto.getPrice()).build();
    }

    public List<bookDto> getDtofromBookEntities(List<Book> books)
    {
        List<bookDto> bookdtos= new ArrayList<>();
        for(Book book: books)
        {
            bookdtos.add(getDtofromBookEntity(book));
        }
        return bookdtos;
    }
    public bookDto getDtofromBookEntity(Book book)
    {
        return bookDto.builder().isbn(book.getIsbn()).name(book.getName()).type(book.getType()).author(book.getAuthor()).price(book.getPrice()).build();
    }
}
