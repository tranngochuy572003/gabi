package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.CategoryDto;
import com.gabispa.restfulservice.entity.Category;
import com.gabispa.restfulservice.repository.ICategoryRepository;
import com.gabispa.restfulservice.service.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
public class CategoryService implements ICategoryService {
  private ICategoryRepository iCategoryRepository;
  @Autowired
  public CategoryService(ICategoryRepository CategoryRepository) {
    this.iCategoryRepository= CategoryRepository;
  }
  @Override
  public void addCategory(Category category) {
    iCategoryRepository.save(category);
  }
  @Override
  public List<Category> getAllCategory() {
    return iCategoryRepository.findAll();
  }
  @Override
  public Category getCategoryById(Long id) {
    Optional<Category> optionalCategory = iCategoryRepository.findById(id);
    return optionalCategory.orElse(null);
  }
  @Override
  public void updateCategory(Long id, CategoryDto categoryDto) {
    Optional<Category> optionalCategory = iCategoryRepository.findById(id);
    if(optionalCategory.isPresent()){
      Category category = optionalCategory.get();
      category.setState(categoryDto.getState());
      category.setDescription(categoryDto.getDescription());
      category.setName(categoryDto.getName());
    }else{
      throw new EntityNotFoundException("Not found category with id "+ id);
    }

  }
  @Override
  public String deleteCategory(Long id) {
    try {
      iCategoryRepository.deleteById(id);
      return "delete category success";

    }catch (EntityNotFoundException e){
      return "categoryId invalid";
    }
  }
}
