package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.SupplyDto;
import com.gabispa.restfulservice.entity.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply")
public class SupplyController {
  @Autowired
  private com.gabispa.restfulservice.service.impl.supplyService supplyService;

  @PostMapping("/add")
  public ResponseEntity<String> addSupply(@RequestBody Supply supply) {
      supplyService.addSupply(supply);
      return new ResponseEntity<>("Add supply success", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<  List<Supply>> supplyList() {
      List<Supply> supplyList = supplyService.getAllSupply();
      return new ResponseEntity<>(supplyList, HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Supply> getSupply(@PathVariable Long id) {
    Supply supply = supplyService.getSupplyById(id);
    return new ResponseEntity<>(supply, HttpStatus.OK);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<String> updateSupply(@PathVariable Long id, @RequestBody SupplyDto supplyDto) {
      supplyService.updateSupply(id, supplyDto);
      return new ResponseEntity<>("Update supply success",HttpStatus.OK);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteSupply(@PathVariable Long id) {
    supplyService.deleteSupply(id);
    return new ResponseEntity<>("Delete user success",HttpStatus.OK);
  }



}
