package com.example.demo_book_management.service.impl;

import com.example.demo_book_management.dto.GenreDTO;
import com.example.demo_book_management.entity.Genre;
import com.example.demo_book_management.exception.ResourceNotFoundException;
import com.example.demo_book_management.mapper.GenreMapper;
import com.example.demo_book_management.repository.GenreRepository;
import com.example.demo_book_management.service.GenreService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService{
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(@RequestBody GenreDTO genreDto) {
        Genre genre = genreMapper.mapToGenre(genreDto);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.mapToGenreDto(savedGenre);
    }

    @Override
    public GenreDTO getGenreById(Long genreId){
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new OpenApiResourceNotFoundException("Genre is not exist with id" + genreId)
        );
        return genreMapper.mapToGenreDto(genre);
    }

    @Override
    public GenreDTO editGenre(Long genreId, GenreDTO updatedGenre) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new OpenApiResourceNotFoundException("Genre is not exist with id" + genreId)
        );

        genre.setName(updatedGenre.getName());

        Genre updatedGenreObj = genreRepository.save(genre);

        return genreMapper.mapToGenreDto(updatedGenreObj);
    }

    @Override
    public GenreDTO deleteGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new ResourceNotFoundException("Genre is not exist with id" + genreId)
        );

        genreRepository.deleteById(genreId);
        return null;
    }
}
