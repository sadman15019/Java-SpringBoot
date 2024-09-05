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

    @Column(name="Discount_rate", nullable = false)
    private float discount_rate;

    @ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH} )  // many books can be in one particular shop
    @JoinColumn(name="SHOP_ID", nullable = false)   // here shop id is the foreign key which is primary key for bookshop
    @JsonBackReference
    private BookShop bookshop;

}
