package com.group.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.group.project.dto.UserDTO;
import com.group.project.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}
