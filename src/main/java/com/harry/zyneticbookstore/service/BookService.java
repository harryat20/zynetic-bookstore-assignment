package com.harry.zyneticbookstore.service;

import com.harry.zyneticbookstore.dao.BookRepo;
import com.harry.zyneticbookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Sort;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book createBook(Book book){
        return bookRepo.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    public List<Book> getSortedBooks(Sort sort) {
        return bookRepo.findAll(sort);
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

    }

    public Book updateBook(Long id, Book newBook){
        Book book = bookRepo.findById(id).orElse(null);
        if (book != null){
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setCategory(newBook.getCategory());
            book.setPrice(newBook.getPrice());
            book.setRating(newBook.getRating());
            book.setPublishedDate(newBook.getPublishedDate());
            return bookRepo.save(book);
        }
        return null;
    }

    public void deleteBook(Long id){
        bookRepo.deleteById(id);
    }


    public List<Book> getBooksByAuthor(String author) {
        return bookRepo.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> getBooksByCategory(String category) {
        return bookRepo.findByCategoryContainingIgnoreCase(category);
    }

    public List<Book> getBooksByRating(double rating) {
        return bookRepo.findByRatingGreaterThanEqual(rating);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepo.findByTitleContainingIgnoreCase(title);
    }


}
