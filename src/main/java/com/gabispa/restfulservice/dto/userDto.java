package com.gabispa.restfulservice.dto;

import lombok.*;


@Data
@NoArgsConstructor
public class userDto {
 private String state ;

 private String accountName;
 private String email;
 private String password;
 private String address;
 private String birthDay;
 private Long age;
 private String phone;
 private String note;
 private String role;


}
