package com.example.bookinfomanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity  // declared entity for object relational mapping (ORM) , creating database table and entry from oop objects
public class Book extends baseDomain {
    @Id
    @GeneratedValue
    @Column(name="ID", nullable = false)
    private int id;

    @Column(name="ISBN", nullable = false)
    private String isbn;

    @Column(name="Name", nullable = false)
    private String name;

    @Column(name="Type", nullable = false)
    private String type;

    @Column(name="Author", nullable = false)
    private String author;

    @Column(name="Price", nullable = false)
    private int price;

}
