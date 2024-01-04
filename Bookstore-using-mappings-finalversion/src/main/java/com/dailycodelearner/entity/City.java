package com.dailycodelearner.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cityid")
    private Long id;
    private String name;

    public City(String name) {
        this.name = name;
    }
}
