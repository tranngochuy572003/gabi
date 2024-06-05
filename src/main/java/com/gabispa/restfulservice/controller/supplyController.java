package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.SupplyDto;
import com.gabispa.restfulservice.entity.Supply;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply")
public class SupplyController {
  @Autowired
  private com.gabispa.restfulservice.service.impl.supplyService supplyService;

  @PostMapping("/add")
  public String addSupply(@RequestBody Supply supply) {
    try {
      supplyService.addSupply(supply);
      return "Add supply success";

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return "error";
    }


  }

  @GetMapping
  public List<Supply> supplyList() {

    try {
      return supplyService.getAllSupply();

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return null;
    }


  }

  @GetMapping("/get/{id}")
  public Supply getSupply(@PathVariable Long id) {
    try {
      return supplyService.getSupplyById(id);

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return null;
    }


  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<Void> updateSupply(@PathVariable Long id, @RequestBody SupplyDto supplyDto) {


    try {
      supplyService.updateSupply(id, supplyDto);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return null;
    }

  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteSupply(@PathVariable Long id) {
    supplyService.deleteSupply(id);
    return ResponseEntity.noContent().build();
  }



}
