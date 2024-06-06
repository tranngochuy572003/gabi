package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.CategoryDto;
import com.gabispa.restfulservice.entity.Category;

import java.util.List;

public interface categoryService {
  void addCategory(Category category);
  List<Category> getAllCategory();
  Category getCategoryById(Long id);
  void updateCategory(Long id, CategoryDto categoryDto);
  void deleteCategory(Long id);


}
