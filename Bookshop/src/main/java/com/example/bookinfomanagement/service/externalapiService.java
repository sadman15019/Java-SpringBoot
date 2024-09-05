package com.example.bookinfomanagement.service;

import com.example.bookinfomanagement.config.ApplicationProperties;
import com.example.bookinfomanagement.dto.bookDto2;
import com.example.bookinfomanagement.dto.isbnList;
import com.example.bookinfomanagement.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class externalapiService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public externalapiService(RestTemplate restTemplate,ApplicationProperties applicationProperties)
    {
        this.applicationProperties= applicationProperties;
        this.restTemplate= restTemplate;
    }

    public List<bookDto2> getbookDetails(List<Book> booklist)
    {
        List<String>isbns =new ArrayList<>();

        for(Book list: booklist)
        {
            isbns.add(list.getIsbn());
        }

        isbnList isbnlist = new isbnList(isbns);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap the ISBN list DTO in HttpEntity
        HttpEntity<isbnList> request = new HttpEntity<>(isbnlist, headers);

        // Build the URL
        String url = applicationProperties.getBookurl();

        // Send the request
        ResponseEntity <List <bookDto2>> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.POST, request,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();  // bookDto2 type is used for showing the response of the details of the books
    }
}
