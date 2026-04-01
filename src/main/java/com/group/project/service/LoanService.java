package com.group.project.service;


import org.springframework.stereotype.Service;

import com.group.project.dto.BookDTO;
import com.group.project.dto.LoanDTO;
import com.group.project.entity.Book;
import com.group.project.entity.Loan;
import com.group.project.entity.User;
import com.group.project.enums.LoanStatus;
import com.group.project.mapper.BookMapper;
import com.group.project.mapper.LoanMapper;
import com.group.project.repository.LoanRepository;
import com.group.project.repository.UserRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository,
                        LoanMapper loanMapper,
                        BookMapper bookMapper,
                        UserRepository userRepository
    ) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
    }

    public void addLoan(String username, BookDTO bookDTO) {

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("username not found"));

        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setBook(bookMapper.toEntity(bookDTO));
        loanDTO.setUser(user);
        loanDTO.setStatus(LoanStatus.BORROWED.toString());

        loanRepository.save(loanMapper.toEntity(loanDTO));
    }

    public LoanDTO returnBookInLoan(String username, Book book) {

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Loan loan = loanRepository.findLatestLoanByUsername(user.getUsername(), book.getId())
            .orElseThrow(() -> new IllegalArgumentException("User has no book loaned"));
        
        loan.setStatus(LoanStatus.RETURNED);

        loanRepository.save(loan);

        return loanMapper.toDTO(loan);

    }
}
