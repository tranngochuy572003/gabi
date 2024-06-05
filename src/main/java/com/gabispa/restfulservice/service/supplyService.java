package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.SupplyDto;
import com.gabispa.restfulservice.entity.Category;
import com.gabispa.restfulservice.entity.Supply;

import java.util.HashMap;
import java.util.List;

public interface supplyService {
  void addSupply(Supply supply);
  List<Supply> getAllSupply();
  Supply getSupplyById(Long id);
  void updateSupply(Long id, SupplyDto supplyDto);
  String deleteSupply(Long id);
  HashMap<String,List<Category>> getSupplyByCategory( );

}
