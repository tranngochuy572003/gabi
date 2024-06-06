package com.gabispa.restfulservice.mapper;

import com.gabispa.restfulservice.dto.categoryDto;
import com.gabispa.restfulservice.entity.Category;

public class categoryMapper {
  public static Category toEntity(categoryDto categoryDto){
    Category category = new Category();
    category.setName(categoryDto.getName());
    category.setDescription(categoryDto.getDescription());
    category.setState(categoryDto.getState());

    return category;

  }

  public static categoryDto toDto(Category category){
    categoryDto categoryDto = new categoryDto();
    categoryDto.setName(category.getName());
    categoryDto.setDescription(category.getDescription());
    categoryDto.setState(category.getState());

    return categoryDto;

  }
}
