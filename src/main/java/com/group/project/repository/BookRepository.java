package com.group.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group.project.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b.id, b.title, b.author FROM Book b WHERE b.borrowed = false")
    List<Book> showAvailableBooks();
}
