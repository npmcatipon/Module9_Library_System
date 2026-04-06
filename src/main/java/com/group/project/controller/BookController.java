package com.group.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
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

/**
 * Controller for managing books.
 * <p>
 * Provides endpoints for creating, updating, deleting, borrowing and returning
 * books.
 * <p>
 * Access is controlled via role-based authentication.
 * - ADMIN: Full Access
 * - USER: View, Borrow and Return Book.
 */

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Display books based on status.
	 * 
	 * @param status
	 *               <ul>
	 *               <li>ALL - Display all books</li>
	 *               <li>AVAILABLE - Display available books</li>
	 *               <li>BORROWED - Display borrowed books</li>
	 *               </ul>
	 * @return list of books.
	 */
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<BookDTO>> getBooks(
			@RequestParam(defaultValue = "ALL") String status) {

		return ResponseEntity.ok(
				switch (status.toUpperCase()) {
					case "AVAILABLE" -> bookService.getAvailableBooks();
					case "BORROWED" -> bookService.getBorrowedBooks();
					default -> bookService.getAllBooks();
				});
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(bookService.createBook(bookDTO));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,
			@Valid @RequestBody BookDTO bookDTO) {

		BookDTO updatedBook = bookService.updateBook(id, bookDTO);
		return ResponseEntity.ok(updatedBook);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	/**
	 * Borrows a book.
	 * <p>
	 * 
	 * @param id          ID of the book to be borrowed.
	 * @param authDetails User details of the borrower.
	 * @return Updated book DTO marked as borrowed.
	 */
	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping("/borrow/{id}")
	public ResponseEntity<BookDTO> borrowBook(@PathVariable Long id, @AuthenticationPrincipal UserDetails authDetails) {
		return ResponseEntity.ok(bookService.borrowBook(id, authDetails.getUsername()));
	}

	/**
	 * Returns a borrowed book.
	 * <p>
	 * 
	 * @param id          ID of the book to be returned.
	 * @param authDetails User details for the book returner.
	 * @return Updated book DTO marked as available.
	 */
	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping("/return/{id}")
	public ResponseEntity<BookDTO> returnBook(@PathVariable Long id, @AuthenticationPrincipal UserDetails authDetails) {
		return ResponseEntity.ok(bookService.returnBook(id, authDetails.getUsername().toString()));
	}

}
