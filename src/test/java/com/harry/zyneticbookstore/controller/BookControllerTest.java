package com.harry.zyneticbookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harry.zyneticbookstore.model.Book;
import com.harry.zyneticbookstore.service.BookService;
import com.harry.zyneticbookstore.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Book sampleBook() {
        return new Book(1L, "Test Book", "Test Author", "Fiction", 299.0, 4.2, LocalDate.of(2023, 1, 1));
    }

    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(sampleBook()));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(sampleBook());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }



    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("BOOK DELETED"));
    }
}
