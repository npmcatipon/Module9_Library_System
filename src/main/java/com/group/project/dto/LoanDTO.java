package com.group.project.dto;

import java.util.List;

import com.group.project.entity.Book;
import com.group.project.entity.User;

public class LoanDTO {

    private Long id;
    private User users;
    private List<Book> books;

    public LoanDTO(Long id, User users, List<Book> books) {
        this.id = id;
        this.users = users;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
