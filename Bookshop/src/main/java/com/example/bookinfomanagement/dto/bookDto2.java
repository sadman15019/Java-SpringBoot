package com.example.bookinfomanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class bookDto2 {
    private String isbn;
    private String name;
    private String type;
    private String author;
    private int price;
}
