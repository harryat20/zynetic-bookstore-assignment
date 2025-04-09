package com.harry.zyneticbookstore.service;

import com.harry.zyneticbookstore.model.Book;
import com.harry.zyneticbookstore.dao.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    private Book mockBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBook = new Book(1L, "Spring in Action", "Craig Walls", "Programming", 450.0, 4.8, LocalDate.of(2023, 10, 15));
    }

    @Test
    void testCreateBook() {
        when(bookRepo.save(mockBook)).thenReturn(mockBook);
        Book savedBook = bookService.createBook(mockBook);
        assertEquals("Spring in Action", savedBook.getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(mockBook));
        Book book = bookService.getBookById(1L);
        assertEquals("Craig Walls", book.getAuthor());
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(mockBook);
        when(bookRepo.findAll()).thenReturn(books);
        assertEquals(1, bookService.getAllBooks().size());
    }
}
