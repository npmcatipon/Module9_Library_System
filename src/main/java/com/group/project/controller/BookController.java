package com.group.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.project.entity.Book;
import com.group.project.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks(
			@RequestParam(required = false) Long id) {
		System.out.println("All Books!!");
		List<Book> books = (id != null)
				? bookService.getById(id)
				: bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

	@GetMapping("/available")
	public ResponseEntity<List<Book>> getAvailBooks(
			@RequestParam(required = false) Long id) {
		List<Book> books = (id != null)
				? bookService.getById(id)
				: bookService.getAvailableBooks();
		return ResponseEntity.ok(books);
	}

	@GetMapping("/borrowed")
	public ResponseEntity<List<Book>> getBorrowedBooks(
			@RequestParam(required = false) Long id) {
		List<Book> books = (id != null)
				? bookService.getById(id)
				: bookService.getBorrowedBooks();
		return ResponseEntity.ok(books);
	}
}
