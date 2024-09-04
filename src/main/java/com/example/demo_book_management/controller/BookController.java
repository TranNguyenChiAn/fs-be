package com.example.demo_book_management.controller;

import com.example.demo_book_management.database.ResponseObject;
import com.example.demo_book_management.dto.BookDTO;
import com.example.demo_book_management.entity.Book;
import com.example.demo_book_management.mapper.BookMapper;
import com.example.demo_book_management.repository.BookRepository;
import com.example.demo_book_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    List<BookDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> findById(@PathVariable("id") Long bookId){
         BookDTO bookDto = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDto){
        BookDTO saveBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long bookId,
                                              @RequestBody BookDTO updatedBook) {
        BookDTO bookDTO = bookService.editBook(bookId,updatedBook);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBook(@PathVariable("id") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Delete book successfully");
    }
}
