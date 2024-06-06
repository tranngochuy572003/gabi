package com.gabispa.restfulservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class taskDto {
  private String name ;
  private String description ;
  private String type;
  private Long timeWork ;
  private String state ;
}
