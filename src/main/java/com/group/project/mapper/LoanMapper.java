package com.group.project.mapper;

import org.mapstruct.Mapper;

import com.group.project.dto.LoanDTO;
import com.group.project.entity.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan toEntity(LoanDTO loanDTO);

    LoanDTO toDTO(Loan loan);
}
