package com.example.goodreadsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.goodreadsApp.model.Author;


@Repository
public interface AuthorJpaRepository extends JpaRepository<Author,Integer> {
}
