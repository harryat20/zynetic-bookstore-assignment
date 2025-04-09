package com.harry.zyneticbookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is requiredd")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotBlank(message = "Category is required")
    private String category;
    @Min(value = 0, message = "Price cannot be negative")
    private double price;
    @DecimalMin(value = "0.0" , inclusive = true, message = "Rating cannot be negative")
    @DecimalMax(value = "5.0" , inclusive = true, message = "Rating cannot exceed 5")
    private double rating;
    @NotNull(message = "Published date is required")
    @Column(name = "published_date")
    private LocalDate publishedDate;

}
