package com.group.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
