package com.example.demo_book_management.controller;

import com.example.demo_book_management.dto.BookDTO;
import com.example.demo_book_management.dto.GenreDTO;
import com.example.demo_book_management.entity.Book;
import com.example.demo_book_management.mapper.BookMapper;
import com.example.demo_book_management.mapper.GenreMapper;
import com.example.demo_book_management.service.GenreService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo_book_management.database.ResponseObject;
import com.example.demo_book_management.entity.Genre;
import com.example.demo_book_management.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreService genreService;

    @GetMapping
    List<GenreDTO> getAllGenre(){
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(genreMapper::mapToGenreDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        Optional<Genre> foundGenre= genreRepository.findById(id);
        if (foundGenre.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query genre successfully", foundGenre)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("false", "Cannot find genre with id = " + id, "")
            );
        }
    }

    @PostMapping
    ResponseEntity<ResponseObject> insertGenre(@RequestBody Genre newGenre){
        List<Genre> foundGenre = genreRepository.findByName(newGenre.getName().trim());

        if(!foundGenre.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Genre name already taken", "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert genre successfully", genreRepository.save(newGenre))
            );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("id") Long genreId,
                                               @RequestBody GenreDTO updatedGenre) {
        GenreDTO genreDTO = genreService.editGenre(genreId,updatedGenre);
        return ResponseEntity.ok(genreDTO);
    }

    @DeleteMapping("{id}")
    ResponseEntity<ResponseObject> deleteGenre(@PathVariable Long id){
        boolean exists = genreRepository.existsById(id);
        if (exists){
            genreRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete genre successful", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find genre to delete", "")
        );
    }
}
