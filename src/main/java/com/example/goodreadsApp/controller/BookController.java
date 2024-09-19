package com.example.goodreadsApp.controller;

import com.example.goodreadsApp.model.Author;
import com.example.goodreadsApp.model.Book;
import com.example.goodreadsApp.model.Publisher;
import com.example.goodreadsApp.service.BookJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
//    BookService bookService = new BookService();
@Autowired
public BookJPAService bookService;
    @GetMapping("/books")
    ArrayList<Book> getBooks(){
        return  bookService.getBooks();
    }
    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable ("bookId") int bookId){
        return  bookService.getBookById(bookId);
    }
    @PostMapping ("/publisher/books")
    public Book addBook(@RequestBody Book book){
      return  bookService.addBook(book);
    }
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable ("bookId") int bookId){
         bookService.deleteBook(bookId);
    }
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable ("bookId") int bookId,@RequestBody Book book){
        return bookService.updateBook(bookId,book );

    }
    @GetMapping("/books/{bookId}/publisher")
    public Publisher getBookPublisher(@PathVariable("bookId") int bookId){
        return  bookService.getBookPublisher(bookId);
    }
    @GetMapping("/books/{bookId}/authors")
    public List<Author> getBookAuthors(@PathVariable("bookId") int bookId) {
        return bookService.getBookAuthors(bookId);
    }
}
