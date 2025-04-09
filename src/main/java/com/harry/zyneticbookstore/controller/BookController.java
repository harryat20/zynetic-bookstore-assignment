package com.harry.zyneticbookstore.controller;

import com.harry.zyneticbookstore.model.Book;
import com.harry.zyneticbookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@Tag(name = "BookController", description = "To perform operations on Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(
            summary = "POST operation on Books",
            description = "It is used to save book object in database"
    )
    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    @Operation(
            summary = "GET operation on Books",
            description = "It is used to retrive book object from database"
    )
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(
            summary = "GET operation on Books",
            description = "Returns books in paginated format."
    )
    @GetMapping("/paginated")
    public ResponseEntity<Page<Book>> getPaginatedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bookService.getBooks(pageable));
    }

    @Operation(
            summary = "Get sorted books",
            description = "Returns books sorted by price or rating."
    )
    @GetMapping("/sorted")
    public ResponseEntity<List<Book>> getSortedBooks(
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        List<Book> books = bookService.getSortedBooks(sort);
        return ResponseEntity.ok(books);
    }

    @Operation(
            summary = "Get a book by ID",
            description = "Fetches a book by its ID.")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(
            summary = "Update a book",
            description = "Updates an existing book by ID.")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by ID.")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "BOOK DELETED";
    }

    @Operation(
            summary = "Filter books",
            description = "Filter books by author, category, or rating.")
    @GetMapping("/filter")
    public List<Book> filterBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double rating
    ) {
        if (author != null) {
            return bookService.getBooksByAuthor(author);
        } else if (category != null) {
            return bookService.getBooksByCategory(category);
        } else if (rating != null) {
            return bookService.getBooksByRating(rating);
        } else {
            return bookService.getAllBooks();
        }
    }

    @Operation(
            summary = "Search books by title",
            description = "Returns books with titles containing the search keyword.")
    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }

}
