package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.categoryDto;
import com.gabispa.restfulservice.entity.Category;
import com.gabispa.restfulservice.exception.idNotFound;
import com.gabispa.restfulservice.exception.invalidFieldException;
import com.gabispa.restfulservice.mapper.categoryMapper;
import com.gabispa.restfulservice.repository.categoryRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
public class categoryService implements com.gabispa.restfulservice.service.categoryService {
  private categoryRepository categoryRepository;
  @Autowired
  public categoryService(categoryRepository categoryRepository) {

    this.categoryRepository = categoryRepository;
  }
  @Override
  public void addCategory(Category category) {
    if(category.getName().isEmpty())
    {
      throw new invalidFieldException("Name of category is not null");
    }
    categoryRepository.save(category);
  }
  @Override
  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }
  @Override
  public Category getCategoryById(Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if(optionalCategory.isPresent()){
      return optionalCategory.get();
    }
    else{
      throw new idNotFound("Id isn't  exist");
    }
  }
  @Override
  public void updateCategory(Long id, categoryDto categoryDto) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if(optionalCategory.isPresent()){
      Category category = optionalCategory.get();
      category= categoryMapper.toEntity(categoryDto);
    }else{
      throw new idNotFound("Not found category with id "+ id);
    }

  }
  @Override
  public void deleteCategory(Long id) {
    Optional<Category> category = categoryRepository.findById(id);
    if(category.isPresent()){
      categoryRepository.deleteById(id);
    }
    else {
      throw new idNotFound("Id isn't exist");
    }
  }
}
