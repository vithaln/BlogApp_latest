 package com.vitu.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitu.code.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
