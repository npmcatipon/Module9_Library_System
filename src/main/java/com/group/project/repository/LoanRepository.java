package com.group.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.project.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
