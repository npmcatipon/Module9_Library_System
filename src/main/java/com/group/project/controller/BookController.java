package com.group.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.project.dto.BookDTO;
import com.group.project.service.BookService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/all")
	public ResponseEntity<List<BookDTO>> getAllBooks(
			@RequestParam(required = false) Long id) {

		List<BookDTO> booksDTO = (id != null)
				? List.of(bookService.getById(id))
				: bookService.getAllBooks();
		return ResponseEntity.ok(booksDTO);

	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/available")
	public ResponseEntity<List<BookDTO>> getAvailBooks(
			@RequestParam(required = false) Long id) {

		List<BookDTO> booksDTO = (id != null)
				? List.of(bookService.getById(id))
				: bookService.getAvailableBooks();
		return ResponseEntity.ok(booksDTO);

	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/borrowed")
	public ResponseEntity<List<BookDTO>> getBorrowedBooks(
			@RequestParam(required = false) Long id) {
		
				List<BookDTO> booksDTO = (id != null)
				? List.of(bookService.getById(id))
				: bookService.getBorrowedBooks();
		return ResponseEntity.ok(booksDTO);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<BookDTO> addBook(@Valid 
											@RequestBody 
											BookDTO bookDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(bookService.create(bookDTO));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("update/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, 
												@RequestBody BookDTO bookDTO) {
		
		BookDTO updatedBook = bookService.update(id, bookDTO);
		return ResponseEntity.ok(updatedBook);
	}

}
