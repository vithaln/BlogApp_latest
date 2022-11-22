package com.vitu.code.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	@Size(min=3,message="categoryTitle must starts at least 3 characters..")
	private String categoryTitle;
	
	@NotEmpty	
	@Size(min=3,message="categoryDescription must starts at least 3 characters..")
	private String categoryDescription;

}
