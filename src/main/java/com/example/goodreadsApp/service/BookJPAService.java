package com.example.goodreadsApp.service;

import com.example.goodreadsApp.model.Author;
import com.example.goodreadsApp.model.Book;
import com.example.goodreadsApp.model.Publisher;
import com.example.goodreadsApp.repository.AuthorJpaRepository;
import com.example.goodreadsApp.repository.BookJpaRepository;
import com.example.goodreadsApp.repository.BookRepository;
import com.example.goodreadsApp.repository.PublisherJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookJPAService implements BookRepository {
    @Autowired
    private BookJpaRepository bookJpaRepository;
    @Autowired
    private PublisherJpaRepository publisherJpaRepository;
    @Autowired
    private AuthorJpaRepository authorJpaRepository;
    @Override
    public ArrayList<Book> getBooks(){
        List<Book> booklist = bookJpaRepository.findAll();
        ArrayList<Book> books = new ArrayList<>(booklist);
        return books;
    }

    public Book getBookById(int bookId) {
        try{

            Book book = bookJpaRepository.findById(bookId).get();
            return book;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Book addBook(Book book) {
        List<Integer> authorIds = new ArrayList<>();
        for( Author  author : book.getAuthors()){
            authorIds.add(author.getAuthorId());
        }
       Publisher publisher = book.getPublisher();
       int publisherId = publisher.getPublisherId();
       try{
           List<Author> completeAuthor = authorJpaRepository.findAllById(authorIds);
           if(authorIds.size() != completeAuthor.size()){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"authors missing");
           }
           book.setAuthors(completeAuthor);
           Publisher completePublisher = publisherJpaRepository.findById(publisherId).get();
           book.setPublisher(completePublisher);
           bookJpaRepository.save(book);
           return book;
       }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong publisherId");
        }
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        try {
            Book newBook =bookJpaRepository.findById(bookId).get();
            if (book.getName() != null) {
                newBook.setName(book.getName());
            }
            if (book.getImageUrl() != null) {
                newBook.setImageUrl(book.getImageUrl());
            }
            bookJpaRepository.save(newBook);

            return newBook;

        } catch(Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Publisher getBookPublisher(int bookId) {
        try{
            Book book = bookJpaRepository.findById(bookId).get();
            return book.getPublisher();
        }catch(Exception e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Author> getBookAuthors(int bookId) {
        try{
            Book book = bookJpaRepository.findById(bookId).get();
            return book.getAuthors();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteBook(int bookId) {
        try {

            bookJpaRepository.deleteById(bookId);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}

