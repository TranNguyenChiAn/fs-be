package com.example.demo_book_management.service;

import com.example.demo_book_management.dto.GenreDTO;
import org.springframework.stereotype.Service;

@Service
public interface GenreService {
    GenreDTO createGenre(GenreDTO genreDto);
    GenreDTO getGenreById(Long genreId);
    GenreDTO editGenre(Long genreId, GenreDTO updatedGenre);
    GenreDTO deleteGenre(Long genreId);
}
