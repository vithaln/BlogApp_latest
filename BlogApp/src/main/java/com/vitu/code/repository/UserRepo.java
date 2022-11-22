package com.vitu.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitu.code.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
