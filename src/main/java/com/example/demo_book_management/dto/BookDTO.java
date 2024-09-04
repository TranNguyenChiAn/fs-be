package com.example.demo_book_management.dto;

import com.example.demo_book_management.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Genre genre_id;
    private String summary;
    private int page_number;
    private int quantity;
    private double price;
}
