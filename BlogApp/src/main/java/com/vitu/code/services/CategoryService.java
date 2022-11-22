package com.vitu.code.services;

import java.util.List;

import com.vitu.code.payload.CategoryDto;

public interface CategoryService {

	
	CategoryDto createCategories(CategoryDto categoryDto);
	List<CategoryDto> getAllCategories();
	CategoryDto getCategoryById(int categoryId);
	CategoryDto upadteCategories(CategoryDto categoryDto, int categoryId);
	void deleteCategoryById(int categoryId);
	
}
