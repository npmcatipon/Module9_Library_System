package com.group.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.project.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b.id, b.title, b.author FROM Book b")
    List<Book> showAllBooks();

    @Query("SELECT b.id, b.title, b.author FROM Book b WHERE b.borrowed = false")
    List<Book> showAvailableBooks();

    @Query("SELECT b.id, b.title, b.author FROM Book b WHERE b.borrowed = true")
    List<Book> showBorrowedBooks();
}
