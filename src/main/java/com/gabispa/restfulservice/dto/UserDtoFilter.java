package com.gabispa.restfulservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoFilter {
  private String accountName;
  private String state ;

}
