package com.gabispa.restfulservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class userDtoLogin {
    private String email;
    private String password;
}
