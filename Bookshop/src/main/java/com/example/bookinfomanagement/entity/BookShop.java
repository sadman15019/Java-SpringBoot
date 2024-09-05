package com.example.bookinfomanagement.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Bookshop")
public class BookShop extends baseDomain{
    @Id
    @GeneratedValue
    @Column(name="SHOP_ID", nullable = false)
    private int id;

    @Column(name="Name", nullable = false)
    private String name;

    @Column(name="Shop_No", nullable = false)
    private String shopNo;

    @Column(name="Address", nullable = false)
    private String address;

    @Column(name="Hotline", nullable = false)
    private String hotLine;

    @OneToMany(mappedBy = "bookshop",fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)  // mapped by needs to be the refernce name that is used in Book
    @JsonManagedReference
    private List<Book> books;

}
