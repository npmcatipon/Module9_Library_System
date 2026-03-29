package com.group.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group.project.repository.BookRepository;
import com.group.project.entity.Book;

@Service
public class BookService {

	private final BookRepository bookRepository;

	List<Book> books = new ArrayList<>();

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks() {
		return bookRepository.showAllBooks();
	}

	public List<Book> getAvailableBooks() {
		return bookRepository.showAvailableBooks();
	}

	public List<Book> getBorrowedBooks() {
		return bookRepository.showBorrowedBooks();
	}

	public List<Book> getById(Long id) {
		return bookRepository.findById(id).stream().toList();
	}
}
