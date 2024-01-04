package com.dailycodelearner.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookid")
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryid")
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = {
                    @JoinColumn(name = "book_id", referencedColumnName = "bookid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "authorid")
            }
    )
    private List<Author> authors;

    public Book(String name, Category category, List<Author> authors) {
        this.name = name;
        this.category = category;
        this.authors = authors;
    }
}
