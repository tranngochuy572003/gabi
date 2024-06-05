package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.CategoryDto;
import com.gabispa.restfulservice.entity.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
  private com.gabispa.restfulservice.service.impl.categoryService categoryService;

  @Autowired
  public CategoryController(com.gabispa.restfulservice.service.impl.categoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping("/add")
  public String add(@RequestBody Category category) {
    try {
      categoryService.addCategory(category);
      return "add category success";

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return "error";
    }
  }


  @PatchMapping("/update/{id}")
  public String update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    try {
      categoryService.updateCategory(id, categoryDto);
      return "update category success";

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return "error";
    }
  }

  @GetMapping()
  public List<Category> categoryList() {
    try {
      return categoryService.getAllCategory();


    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  @GetMapping("get/{id}")
  public Category category(@PathVariable Long id) {
    try {
      return categoryService.getCategoryById(id);

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  @DeleteMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    try {
      categoryService.deleteCategory(id);
      return "delete category success";

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return "error";
    }


  }

}
