package com.example.demo_book_management.entity;


import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increment
    private Long id;

    @Column(nullable = false, unique = true,length = 300)
    private String title;

    @Column(nullable = false,length = 300)
    private String author;

    @Column(nullable = false,length = 10)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre_id;

    @Column(nullable = false,length = 10)
    private String summary;

    @Column(nullable = false,length = 10)
    private int page_number;

    @Column(nullable = false,length = 10)
    private int quantity;


    public Book(Long id, String title, String author, Genre genre_id,
                String summary, int page_number, int quantity, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre_id = genre_id;
        this.summary = summary;
        this.page_number = page_number;
        this.quantity = quantity;
        this.price = price;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Genre getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Genre genre_id) {
        this.genre_id = genre_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id" + id +
                ", title: " + title + '\'' +
                ", author: " + author +
                ", price: " + price +
                ", genre_id: " + genre_id +
                ", summary: " + summary +
                ", page_number: " + page_number +
                ", quantity: " + quantity + '\'' +
                '}';
    }
}
