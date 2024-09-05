package com.example.bookinfomanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class bookshopDto2 {
    private String name;
    private String shopNo;
    private String address;
    private String hotLine;
    private List<bookDto2> books;
}
