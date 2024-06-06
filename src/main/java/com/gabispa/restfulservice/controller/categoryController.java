package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.categoryDto;
import com.gabispa.restfulservice.entity.Category;
import com.gabispa.restfulservice.exception.listNullException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class categoryController {
  private com.gabispa.restfulservice.service.impl.categoryService categoryService;

  @Autowired
  public categoryController(com.gabispa.restfulservice.service.impl.categoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping("/add")
  public ResponseEntity<?> add(@RequestBody Category category) {
    categoryService.addCategory(category);
    return new ResponseEntity<>("Add category success", HttpStatus.OK);
  }


  @PatchMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody categoryDto categoryDto) {
    categoryService.updateCategory(id, categoryDto);
    return new ResponseEntity<>("Update category success", HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<Category>> categoryList() {
    List<Category> categoryList=categoryService.getAllCategory();
    if(categoryList.isEmpty()){
      throw new listNullException("CategoryList return null");
    }
    else {
      return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
  }

  @GetMapping("get/{id}")
  public ResponseEntity<Category> category(@PathVariable Long id) {
     Category category= categoryService.getCategoryById(id);
     return new ResponseEntity<>(category, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public  ResponseEntity<String> delete(@PathVariable Long id) {
      categoryService.deleteCategory(id);
      return new ResponseEntity<>("Delete category success", HttpStatus.OK);
  }

}
