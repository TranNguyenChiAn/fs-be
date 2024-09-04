package com.example.demo_book_management.repository;

import com.example.demo_book_management.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);
    List<Genre> findAll();
    Optional<Genre> findById(Long id);
}
