package com.example.demo_book_management.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="genres")
public class Genre {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false, unique = true, length = 300)
    public String name;


    //constructor
    public Genre() {

    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id" + id +
                ", name: " + name +
                '}';
    }
}
