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
import com.group.project.dto.UserDTO;
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

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<BookDTO>> getBooks(
		@RequestParam(defaultValue = "ALL") String status) {

			return ResponseEntity.ok(
				switch(status.toUpperCase()) {
					case "AVAILABLE" -> bookService.getAvailableBooks();
					case "BORROWED" -> bookService.getBorrowedBooks();
					default -> bookService.getAllBooks();
				}
			);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<BookDTO> addBook(@Valid 
											@RequestBody BookDTO bookDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(bookService.createBook(bookDTO));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("update/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, 
												@RequestBody BookDTO bookDTO) {
		
		BookDTO updatedBook = bookService.updateBook(id, bookDTO);
		return ResponseEntity.ok(updatedBook);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping("/borrow/{id}")
	public ResponseEntity<BookDTO> borrowBook(@PathVariable Long id, @AuthenticationPrincipal UserDetails authDetails) {
		return ResponseEntity.ok(bookService.borrowBook(id,authDetails.getUsername()));
	}

	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping("/return/{id}")
	public ResponseEntity<BookDTO> returnBook(@PathVariable Long id, @AuthenticationPrincipal UserDetails authDetails){ 
		return ResponseEntity.ok(bookService.returnBook(id, authDetails.getUsername().toString()));
	}

}
