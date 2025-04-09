package com.harry.zyneticbookstore.dao;

import com.harry.zyneticbookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByCategoryContainingIgnoreCase(String category);

    List<Book> findByRatingGreaterThanEqual(double rating);

    List<Book> findByTitleContainingIgnoreCase(String title);

}
