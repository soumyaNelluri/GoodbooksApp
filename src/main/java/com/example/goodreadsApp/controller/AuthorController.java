package com.example.goodreadsApp.controller;


import com.example.goodreadsApp.model.Author;
import com.example.goodreadsApp.model.Book;
import com.example.goodreadsApp.service.AuthorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorJpaService authorJpaService;

    @GetMapping("/authors")
    public ArrayList<Author> getAuthors(){
        return authorJpaService.getAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable("id") int id){
        return authorJpaService.getAuthorById(id);
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author){
        return authorJpaService.addAuthor(author);
    }

    @PutMapping("/authors/{id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable("id") int id) {
        return authorJpaService.updateAuthor(id, author);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable("id") int id) {
        authorJpaService.deleteAuthor(id);
    }

    @GetMapping("/authors/{authorId}/books")
    public List<Book> getAuthorsBooks(@PathVariable("authorId") int authorId){
        return authorJpaService.getAuthorsBooks( authorId);
    }
}
