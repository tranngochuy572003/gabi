package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.SupplyDto;
import com.gabispa.restfulservice.entity.Category;
import com.gabispa.restfulservice.entity.Supply;
import com.gabispa.restfulservice.repository.ISupplyRepository;
import com.gabispa.restfulservice.service.ISupplyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
public class SupplyService implements ISupplyService {
  private ISupplyRepository ISupplyRepository;

  public SupplyService(com.gabispa.restfulservice.repository.ISupplyRepository ISupplyRepository, com.gabispa.restfulservice.service.ISupplyService ISupplyService) {
    this.ISupplyRepository = ISupplyRepository;
  }
  @Override
  public void addSupply(Supply supply) {
    ISupplyRepository.save(supply);
  }
  @Override
  public List<Supply> getAllSupply() {
    return ISupplyRepository.findAll();
  }
  @Override
  public Supply getSupplyById(Long id) {
    Optional<Supply> optionalSupply  = ISupplyRepository.findById(id);
    return optionalSupply.orElse(null);
  }
  @Override
  public void updateSupply(Long id, SupplyDto supplyDto) {
    Optional<Supply> optionalSupply = ISupplyRepository.findById(id);
    if(optionalSupply.isPresent()){
      Supply supply = optionalSupply.get();
      supply.setNameService(supplyDto.getNameService());
      supply.setPriceService(supplyDto.getPriceService());
      supply.setDescription(supplyDto.getDescription());
      supply.setImage(supplyDto.getImage());
      supply.setRate(supplyDto.getState());
      ISupplyRepository.save(supply);
    }
    else {
      throw new EntityNotFoundException("Supply not found with id: " + id);
    }

  }
  @Override
  public String deleteSupply(Long id) {
    try {
      ISupplyRepository.deleteById(id);
      return "delete supply success";

    }catch (EntityNotFoundException e){

        return "supplyId not found";
    }
  }

  @Override
  public HashMap<String,List<Category>> getSupplyByCategory() {
    HashMap<String, List<Category>> hashMapSupply = new HashMap<>();
    // Lấy danh sách tất cả các Supply từ repository
    List<Supply> supplyList = ISupplyRepository.findAll();
    // Duyệt qua từng Supply
    for (Supply supply : supplyList) {
      // Lấy tên dịch vụ từ Supply
      String nameService = supply.getNameService();
      // Lấy Category từ Supply
      Category category = supply.getCategory();
      // Nếu tên dịch vụ chưa tồn tại trong HashMap, thêm một mục mới với danh sách trống
      if (!hashMapSupply.containsKey(nameService)) {
        hashMapSupply.put(nameService, new ArrayList<>());
      }
      // Thêm Category vào danh sách tương ứng với tên dịch vụ
      hashMapSupply.get(nameService).add(category);
    }
    return hashMapSupply;
  }


}
