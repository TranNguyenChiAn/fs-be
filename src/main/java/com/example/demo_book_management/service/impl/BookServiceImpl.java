package com.example.demo_book_management.service.impl;

import com.example.demo_book_management.dto.BookDTO;
import com.example.demo_book_management.entity.Book;
import com.example.demo_book_management.entity.Genre;
import com.example.demo_book_management.exception.ResourceNotFoundException;
import com.example.demo_book_management.mapper.BookMapper;
import com.example.demo_book_management.repository.BookRepository;
import com.example.demo_book_management.repository.GenreRepository;
import com.example.demo_book_management.service.BookService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookDTO createBook(@RequestBody BookDTO bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new OpenApiResourceNotFoundException("Book is not exist with id" + bookId)
        );
        return bookMapper.mapToBookDto(book);
    }

    @Override
    public BookDTO editBook(Long bookId, BookDTO updatedBook) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new OpenApiResourceNotFoundException("Book is not exist with id" + bookId)
        );

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setGenre_id(updatedBook.getGenre_id());
        book.setSummary(updatedBook.getSummary());
        book.setQuantity(updatedBook.getQuantity());
        book.setPrice(updatedBook.getPrice());
        book.setPage_number(updatedBook.getPage_number());

        Book updatedBookObj = bookRepository.save(book);

        return bookMapper.mapToBookDto(updatedBookObj);
    }

    @Override
    public BookDTO deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book is not exist with id" + bookId)
        );

        bookRepository.deleteById(bookId);
        return null;
    }
}
