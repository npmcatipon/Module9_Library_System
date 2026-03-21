package com.group.project.dto;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private Boolean borrowed;

    public BookDTO() {
    }

    public BookDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookDTO(Long id, String title, String author, Boolean borrowed) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

}
