package com.group.project.mapper;

import org.mapstruct.Mapper;

import com.group.project.dto.BookDTO;
import com.group.project.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    
    Book toEntity(BookDTO bookDTO);

    BookDTO toDto(Book book);
    
}
