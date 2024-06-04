package com.gabispa.restfulservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoLogin {
    private String email;
    private String password;
}
