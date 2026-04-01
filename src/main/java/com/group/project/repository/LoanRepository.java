package com.group.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group.project.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("""
        SELECT l
        FROM Loan l
        WHERE l.user.username = :username
        AND l.status = 'BORROWED'
        AND l.book.id = :id
        ORDER BY l.id DESC
        """)
    public Optional<Loan> findLatestLoanByUsername(String username,Long id);

}
