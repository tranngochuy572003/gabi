package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.categoryDto;
import com.gabispa.restfulservice.entity.Category;

import java.util.List;

public interface categoryService {
  void addCategory(Category category);
  List<Category> getAllCategory();
  Category getCategoryById(Long id);
  void updateCategory(Long id, categoryDto categoryDto);
  void deleteCategory(Long id);


}
