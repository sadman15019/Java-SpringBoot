package com.example.bookinfomanagement.controller;

import com.example.bookinfomanagement.dto.isbnList;
import com.example.bookinfomanagement.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.bookinfomanagement.service.bookService;
import com.example.bookinfomanagement.dto.bookDto;

import java.util.ArrayList;
import java.util.List;


@RestController  // api for rest api
@RequestMapping("/api/v1/book/202413")  //url for showing api calling json value
public class bookController {


    private final bookService bookservice;

    @Autowired   //autowired annotations automatically creates a bookservices java bean
    public bookController(bookService bookservice) {
        this.bookservice = bookservice;
    }

    @GetMapping    // annotation for getting all the values using api
    public List<bookDto> get()
    {
        return bookservice.getAll();
    }

    @GetMapping ("/{isbn}")  // annotation for getting info of a a particular book using id
    public bookDto getBook(@PathVariable String isbn)
    {
        return bookservice.getBook(isbn);
    }


    @PostMapping ("/bookdetails")  // annotation for getting info of a a particular book using id
    public List<bookDto> getBookDetails(@RequestBody isbnList isbnlist) {
        List<bookDto>bookdetailsList=new ArrayList<>();
        for (String x : isbnlist.getIsbnlist())
        {
            bookdetailsList.add(bookservice.getBook(x));
        }

        return bookdetailsList;
    }


    @PostMapping  // annotation for posting data using  api
    public bookDto addBook(@RequestBody bookDto book)
    {
        return bookservice.addBook(book);
    }  // requesbody catches the json code for posting data and pass it to sevices

    @DeleteMapping (value="/{isbn}")  // deleting an entry using book id

    public void deleteBook(@PathVariable String isbn)
    {
        bookservice.deleteBook(isbn);
    }

    @PutMapping(value="/{isbn}")  // updating existing book using corresponding id

    public void updateBook(@PathVariable String isbn, @RequestBody bookDto updatedbook)
    {
        bookservice.updateBook(isbn,updatedbook);
    }

}
