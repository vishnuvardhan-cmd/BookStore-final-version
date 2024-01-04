package com.dailycodelearner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Zipcode")
public class Zipcode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="zipcodeid")
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY,targetEntity = City.class,cascade = CascadeType.ALL)
    @JoinColumn(name="city_id",referencedColumnName = "cityid")
    private City city;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "zipcode")
    private List<Author> authors;

    public Zipcode(String name, City city, List<Author> authors) {
        this.name = name;
        this.city = city;
        this.authors = authors;
    }
}
