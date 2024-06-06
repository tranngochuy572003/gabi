package com.gabispa.restfulservice.mapper;

import com.gabispa.restfulservice.dto.SupplyDto;
import com.gabispa.restfulservice.entity.Supply;

public class supplyMapper {
  public static Supply toEntity(SupplyDto supplyDto){
    Supply supply = new Supply();
    supply.setImage(supplyDto.getImage());
    supply.setDescription(supplyDto.getDescription());
    supply.setPriceService(supplyDto.getPriceService());
    supply.setNameService(supplyDto.getImage());
    return supply;


  }
  public static SupplyDto toDto(Supply supply){
    SupplyDto supplyDto = new SupplyDto();
    supplyDto.setImage(supply.getImage());
    supplyDto.setDescription(supply.getDescription());
    supplyDto.setPriceService(supply.getPriceService());
    supplyDto.setNameService(supply.getImage());
    return supplyDto;

  }
}
