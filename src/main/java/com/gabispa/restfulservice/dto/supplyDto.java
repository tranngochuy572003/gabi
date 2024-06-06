package com.gabispa.restfulservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class supplyDto {
  private String nameService ;
  private Long priceService ;
  private String description ;
  private String image ;
  private String state ;

}
