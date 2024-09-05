package com.example.bookinfomanagement.controller;

import com.example.bookinfomanagement.dto.bookshopDto;
import com.example.bookinfomanagement.dto.bookshopDto2;
import com.example.bookinfomanagement.service.bookshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* In the book comtroller, all operations are either on DTO or primary key of Entity. DTO is received as json api */
@RestController  // api for rest api
@RequestMapping("/api/v1/bookshop/202413")  //url for showing api calling json value
public class bookshopController {


    private final bookshopService bookshopservice;

    @Autowired   //autowired annotations automatically creates a bookservices java bean
    public bookshopController(bookshopService bookshopservice) {
        this.bookshopservice = bookshopservice;
    }

    @GetMapping    // annotation for getting all the values using api
    public List<bookshopDto> get()
    {
        return bookshopservice.getAll(); // to get all the bookshops information
    }

    @GetMapping ("/{shopNo}")  // annotation for getting info of a particular bookshop using shopNo
    public bookshopDto2 getBookShop(@PathVariable String shopNo)  // pathvariable means passing a shopNo to url
    {
        return bookshopservice.getBookShop(shopNo);
    }

    @PostMapping  // annotation for posting data using  api
    public bookshopDto addBookShop(@RequestBody @Validated bookshopDto request)
    {
        return bookshopservice.addBookShop(request); // adding a bookshop
    }  // requesbody catches the json code for posting data and pass it to sevices

    @DeleteMapping ("/{shopNo}")  // deleting a bookshop using shopNo

    public void deleteBookShop(@PathVariable String shopNo) // pathvariable means passing a shopNo to url
    {
        bookshopservice.deleteBookShop(shopNo);
    }

    @PutMapping("/{shopNo}")  // updating existing bookshop using shopNo

    public bookshopDto updateBookShop(@PathVariable String shopNo, @RequestBody @Validated bookshopDto request)  // pathvariable means passing a shopNo to url
    {
        return bookshopservice.updateBookShop(shopNo ,request);
    }

}
