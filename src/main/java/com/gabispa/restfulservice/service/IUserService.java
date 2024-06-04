package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.UserDto;
import com.gabispa.restfulservice.entity.User;

import java.util.List;

public interface IUserService {
  void createUser(User user);
  List<User> getAllUsers();
  User getUserById(Long id);
  void updateStateUser(Long id, UserDto userDto);
  String deleteUser(Long userId);
  String updateUser(Long id , UserDto userDto);
  User findUserByEmailAndPassWord(String email , String password);
}
