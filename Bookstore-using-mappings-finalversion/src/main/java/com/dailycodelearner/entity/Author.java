package com.dailycodelearner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="authorid")
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Zipcode.class)
    @JoinColumn(name="zipcode_id",referencedColumnName = "zipcodeid")
    private Zipcode zipcode;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "authors")
    private List<Book> books;

    public Author(String name, Zipcode zipcode, List<Book> books) {
        this.name = name;
        this.zipcode = zipcode;
        this.books = books;
    }
}
