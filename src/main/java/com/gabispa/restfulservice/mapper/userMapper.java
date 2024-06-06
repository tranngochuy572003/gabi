package com.gabispa.restfulservice.mapper;

import com.gabispa.restfulservice.dto.UserDto;
import com.gabispa.restfulservice.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class userMapper {
  private static final String PATTERN = "dd/MM/yyyy";

  public static UserDto toDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setAge(user.getAge());
    userDto.setEmail(user.getEmail());
    userDto.setNote(user.getNote());
    userDto.setAddress(user.getNote());
    userDto.setPassword(user.getNote());
    userDto.setRole(user.getNote());
    userDto.setPhone(user.getPhone());
    userDto.setAccountName(user.getAccountName());

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);

    try {
      Date date = simpleDateFormat.parse(userDto.getBirthDay());
      user.setBirthDay(date);
    }catch (DateTimeParseException | ParseException e){
      e.printStackTrace();

    }
    return userDto;
  }

  public static User toEntity(UserDto userDto) {
    User user = new User();
    user.setAge(userDto.getAge());
    user.setEmail(userDto.getEmail());
    user.setNote(userDto.getNote());
    user.setAddress(userDto.getNote());
    user.setPassword(userDto.getNote());
    user.setRole(userDto.getNote());
    user.setPhone(userDto.getPhone());
    user.setAccountName(userDto.getAccountName());
    return user;
  }
}
