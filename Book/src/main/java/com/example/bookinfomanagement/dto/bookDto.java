package com.example.bookinfomanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class bookDto {
    private String isbn;
    private String name;
    private String type;
    private String author;
    private int price;
}
