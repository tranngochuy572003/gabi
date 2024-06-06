package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.supplyDto;
import com.gabispa.restfulservice.entity.Category;
import com.gabispa.restfulservice.entity.Supply;
import com.gabispa.restfulservice.exception.idNotFound;
import com.gabispa.restfulservice.exception.invalidFieldException;
import com.gabispa.restfulservice.exception.listNullException;
import com.gabispa.restfulservice.mapper.supplyMapper;
import com.gabispa.restfulservice.repository.supplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
public class supplyService implements com.gabispa.restfulservice.service.supplyService {
  private supplyRepository supplyRepository;

  public supplyService(supplyRepository supplyRepository, com.gabispa.restfulservice.service.supplyService ISupplyService) {
    this.supplyRepository = supplyRepository;
  }
  @Override
  public void addSupply(Supply supply) {
    if(supply.getNameService().isEmpty()){
      throw new invalidFieldException("nameField is required");
    }
    supplyRepository.save(supply);
  }
  @Override
  public List<Supply> getAllSupply() {
    List<Supply> supplyList = supplyRepository.findAll();
    if(supplyList.isEmpty()){
      throw new listNullException("List supply return null");
    }
    return supplyList;
  }
  @Override
  public Supply getSupplyById(Long id) {
    Optional<Supply> optionalSupply  = supplyRepository.findById(id);
    if(optionalSupply.isPresent()){
      return optionalSupply.get();
    }
    else {
      throw new idNotFound("supplyId isn't not found");
    }

  }
  @Override
  public void updateSupply(Long id, supplyDto supplyDto) {
    Optional<Supply> optionalSupply = supplyRepository.findById(id);
    if(optionalSupply.isPresent()){
      Supply supply = optionalSupply.get();
      supply = supplyMapper.toEntity(supplyDto);
      supplyRepository.save(supply);
    }
    else {
      throw new idNotFound("Supply not found with id: " + id);
    }

  }
  @Override
  public void deleteSupply(Long id) {
    Optional<Supply> optionalSupply = supplyRepository.findById(id);
    if(optionalSupply.isPresent()){
      supplyRepository.deleteById(id);
    }
    else{
      throw new idNotFound("supplyId isn't exist");
    }

  }

  @Override
  public HashMap<String,List<Category>> getSupplyByCategory() {
    HashMap<String, List<Category>> hashMapSupply = new HashMap<>();
    List<Supply> supplyList = supplyRepository.findAll();
    for (Supply supply : supplyList) {
      String nameService = supply.getNameService();
      Category category = supply.getCategory();
      if (!hashMapSupply.containsKey(nameService)) {
        hashMapSupply.put(nameService, new ArrayList<>());
      }
      hashMapSupply.get(nameService).add(category);
    }
    return hashMapSupply;
  }


}
