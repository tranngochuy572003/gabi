package com.gabispa.restfulservice.mapper;

import com.gabispa.restfulservice.dto.CategoryDto;
import com.gabispa.restfulservice.entity.Category;

public class categoryMapper {
  public static Category toEntity(CategoryDto categoryDto){
    Category category = new Category();
    category.setName(categoryDto.getName());
    category.setDescription(categoryDto.getDescription());
    category.setState(categoryDto.getState());

    return category;

  }

  public static CategoryDto toDto(Category category){
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setName(category.getName());
    categoryDto.setDescription(category.getDescription());
    categoryDto.setState(category.getState());

    return categoryDto;

  }
}
