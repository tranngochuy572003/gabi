package com.gabispa.restfulservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class SupplyDto {
  private String nameService ;
  private Long priceService ;
  private String description ;
  private String image ;
  private String state ;

}
