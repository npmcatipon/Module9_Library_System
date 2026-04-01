package com.group.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.group.project.dto.BookDTO;
import com.group.project.dto.UserDTO;
import com.group.project.entity.Book;
import com.group.project.mapper.BookMapper;
import com.group.project.repository.BookRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class BookService {

    private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	private final LoanService loanService;

	public BookService(BookRepository bookRepository,
		BookMapper bookMapper,
		LoanService loanService) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
		this.loanService = loanService;
	}

	public List<BookDTO> getAllBooks() {
		return bookRepository
				.showAllBooks()
			   	.stream()
			   	.map(bookMapper::toDto)
			   	.toList();
	}

	public List<BookDTO> getAvailableBooks() {
		return bookRepository
				.showAvailableBooks()
				.stream()
				.map(bookMapper::toDto)
				.toList();
	}

	public List<BookDTO> getBorrowedBooks() {
		return bookRepository
				.showBorrowedBooks()
				.stream()
				.map(bookMapper::toDto)
				.toList();
	}

	public BookDTO getById(Long id) {
		Book book = bookRepository
					.findById(id)
					.orElseThrow(() -> new RuntimeException("Book not found."));
		return bookMapper.toDto(book);
	}

	public BookDTO createBook(@Valid BookDTO bookDTO) {
		Book newBook = bookRepository.save(bookMapper.toEntity(bookDTO));
		BookDTO newBookDTO = bookMapper.toDto(newBook);
		return newBookDTO;
	}

	public BookDTO updateBook(Long id, BookDTO updatedBook) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));
		
		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		book.setBorrowed(updatedBook.getBorrowed());

		Book savedBook = bookRepository.save(book);

		return bookMapper.toDto(savedBook);
	}

	public BookDTO deleteBook(Long id) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Book ID not found"));

		bookRepository.delete(book);

		return bookMapper.toDto(book);
	}

	public BookDTO borrowBook(Long id, String user) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Book ID not found"));

		book.setBorrowed(true);

		loanService.addLoan(user, bookMapper.toDto(book));

		book = bookRepository.save(book);
		
		return bookMapper.toDto(book);
	}

	public BookDTO returnBook(Long id, String username) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Book ID not found"));

		book.setBorrowed(false);

		loanService.returnBookInLoan(username, book);

		Book borrowedBook = bookRepository.save(book);
		return bookMapper.toDto(borrowedBook);
	}

}
