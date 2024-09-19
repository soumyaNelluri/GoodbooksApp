package com.example.goodreadsApp.repository;



import com.example.goodreadsApp.model.Author;
import com.example.goodreadsApp.model.Book;

import java.util.ArrayList;
import java.util.List;

public interface AuthorRepository {
    ArrayList<Author> getAuthors();

    Author getAuthorById(int authorId);

    Author addAuthor(Author author);

    Author updateAuthor(int authorId, Author author);

    void deleteAuthor(int authorId);

    List<Book> getAuthorsBooks(int authorId);
}

