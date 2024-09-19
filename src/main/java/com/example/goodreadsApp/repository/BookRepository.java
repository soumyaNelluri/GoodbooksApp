package com.example.goodreadsApp.repository;

import com.example.goodreadsApp.model.Author;
import com.example.goodreadsApp.model.Book;
import com.example.goodreadsApp.model.Publisher;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository {
    ArrayList<Book> getBooks();
    Book getBookById(int bookId);
    Book addBook(Book book);
    void deleteBook(int bookId);
    Book updateBook(int bookId, Book book);
    Publisher getBookPublisher(int bookId);
    List<Author> getBookAuthors(int bookId);
}
