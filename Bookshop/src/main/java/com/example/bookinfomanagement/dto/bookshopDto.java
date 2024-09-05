package com.example.bookinfomanagement.dto;

import com.example.bookinfomanagement.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class bookshopDto {
    private String name;
    private String shopNo;
    private String address;
    private String hotLine;
    private List<bookDto> books;
}
