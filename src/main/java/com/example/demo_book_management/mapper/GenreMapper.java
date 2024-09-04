package com.example.demo_book_management.mapper;

import com.example.demo_book_management.dto.GenreDTO;
import com.example.demo_book_management.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public GenreDTO mapToGenreDto(Genre genre) {
        return new GenreDTO(
                genre.getId(),
                genre.getName()
        );
    }

    public Genre mapToGenre(GenreDTO genreDto) {
        return new Genre(
                genreDto.getId(),
                genreDto.getName()
        );
    }
}
