package com.gabispa.restfulservice.mapper;

import com.gabispa.restfulservice.dto.supplyDto;
import com.gabispa.restfulservice.entity.Supply;

public class supplyMapper {
  public static Supply toEntity(supplyDto supplyDto){
    Supply supply = new Supply();
    supply.setImage(supplyDto.getImage());
    supply.setDescription(supplyDto.getDescription());
    supply.setPriceService(supplyDto.getPriceService());
    supply.setNameService(supplyDto.getImage());
    return supply;


  }
  public static supplyDto toDto(Supply supply){
    supplyDto supplyDto = new supplyDto();
    supplyDto.setImage(supply.getImage());
    supplyDto.setDescription(supply.getDescription());
    supplyDto.setPriceService(supply.getPriceService());
    supplyDto.setNameService(supply.getImage());
    return supplyDto;

  }
}
