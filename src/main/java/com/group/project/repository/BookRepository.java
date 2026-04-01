package com.group.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.project.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.id")
    List<Book> showAllBooks();

    @Query("SELECT b FROM Book b WHERE b.borrowed = false ORDER BY b.id")
    List<Book> showAvailableBooks();

    @Query("SELECT b FROM Book b WHERE b.borrowed = true ORDER BY b.id")
    List<Book> showBorrowedBooks();
}
