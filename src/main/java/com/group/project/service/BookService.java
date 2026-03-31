package com.group.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group.project.repository.BookRepository;

import jakarta.validation.Valid;

import com.group.project.dto.BookDTO;
import com.group.project.entity.Book;
import com.group.project.mapper.BookMapper;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final BookMapper bookMapper;

	List<Book> books = new ArrayList<>();

	public BookService(BookRepository bookRepository,
		BookMapper bookMapper) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
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

	public BookDTO create(@Valid BookDTO bookDTO) {
		Book newBook = bookRepository.save(bookMapper.toEntity(bookDTO));
		BookDTO newBookDTO = bookMapper.toDto(newBook);
		return newBookDTO;
	}

	public BookDTO update(Long id, @Valid BookDTO updatedBook) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));
		
		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		book.setBorrowed(updatedBook.getBorrowed());

		Book savedBook = bookRepository.save(book);

		return bookMapper.toDto(savedBook);
	}

	public BookDTO delete(Long id) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Book ID not found"));

		bookRepository.delete(book);

		return bookMapper.toDto(book);
	}

}
