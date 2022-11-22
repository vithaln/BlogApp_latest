package com.vitu.code.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitu.code.entity.Category;
import com.vitu.code.exceptions.ResourceNotFoundException;
import com.vitu.code.payload.CategoryDto;
import com.vitu.code.repository.CategoryRepo;
import com.vitu.code.services.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo cateRepo;
	@Override
	public CategoryDto createCategories(CategoryDto categoryDto) {

		Category category = this.DtoTocategory(categoryDto);
		Category savedCategory = cateRepo.save(category);
		
		CategoryDto categoryTODto = this.categoryTODto(savedCategory);
		return categoryTODto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = cateRepo.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map(c-> this.categoryTODto(c)).collect(Collectors.toList());
		
		return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {

		 Category category = cateRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found with this ID "+categoryId));

		 CategoryDto categoryTODto = this.categoryTODto(category);
		return categoryTODto;
	}

	@Override
	public CategoryDto upadteCategories(CategoryDto categoryDto, int categoryId) {

		Category category = cateRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category is not found to update by using this is Id "+categoryId +" please check it yourself.."));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = cateRepo.save(category);
		CategoryDto categoryTODto = this.categoryTODto(updatedCategory);
		
		return categoryTODto;
	}

	@Override
	public void deleteCategoryById(int categoryId) {


		Category category = cateRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category is not found to delete Category by using this ID... "+categoryId));
		
		this.cateRepo.delete(category);
		
	}
	
	private CategoryDto categoryTODto(Category category) {
		
		CategoryDto categoryDto =new CategoryDto();
		categoryDto.setCategoryId(category.getCategoryId());
		categoryDto.setCategoryTitle(category.getCategoryTitle());
		categoryDto.setCategoryDescription(category.getCategoryDescription());
		
		
		return categoryDto;
	}

	private Category DtoTocategory(CategoryDto categoryDto) {
		
		Category category=new Category();
		
		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		return category;
	}
}
