package com.example.demo_book_management.service;
import com.example.demo_book_management.dto.BookDTO;
import org.springframework.stereotype.Service;

@Service
public interface BookService{
    BookDTO createBook(BookDTO bookDto);
    BookDTO getBookById(Long bookId);
    BookDTO editBook(Long bookId, BookDTO updatedBook);
    BookDTO deleteBook(Long bookId);
}
