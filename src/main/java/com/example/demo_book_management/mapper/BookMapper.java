package com.example.demo_book_management.mapper;

import com.example.demo_book_management.dto.BookDTO;
import com.example.demo_book_management.entity.Book;
import com.example.demo_book_management.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final GenreRepository genreRepository;

    @Autowired
    public BookMapper(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public BookDTO mapToBookDto(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre_id(),
                book.getSummary(),
                book.getPage_number(),
                book.getQuantity(),
                book.getPrice()
        );
    }

    public Book mapToBook(BookDTO bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre_id(),
                bookDto.getSummary(),
                bookDto.getPage_number(),
                bookDto.getQuantity(),
                bookDto.getPrice()
        );
    }
}
