package com.vitu.code.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitu.code.payload.CategoryDto;
import com.vitu.code.serviceImpl.CategoryImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryImpl categoryImpl;
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategories(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategories = categoryImpl.createCategories(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategories,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		List<CategoryDto> allCategories = categoryImpl.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId){
		
		CategoryDto categoryById = categoryImpl.getCategoryById(categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryById,HttpStatus.OK);
	}
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategoryById(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int categoryId){
		
		CategoryDto categoryById = categoryImpl.upadteCategories(categoryDto, categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryById,HttpStatus.OK);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable int categoryId){
		
		 categoryImpl.deleteCategoryById(categoryId);
		
		return new ResponseEntity<String>("Category has been delted successfully...",HttpStatus.OK);
	}
}
