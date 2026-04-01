package com.group.project.dto;

import com.group.project.entity.Book;
import com.group.project.entity.User;

public class LoanDTO {

    private Long id;
    private User user;
    private Book book;
    private String status;

    public LoanDTO() {
    }

    public LoanDTO(Long id, User user, Book book, String status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}