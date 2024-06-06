package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.userDto;
import com.gabispa.restfulservice.entity.User;
import com.gabispa.restfulservice.exception.invalidFieldException;

import java.util.List;

public interface userService {
  void addUser(User user) throws invalidFieldException;;
  List<User> getAllUsers();
  User getUserById(Long id);
  void updateStateUser(Long id, userDto userDto);
  String deleteUser(Long userId);
  String updateUser(Long id , userDto userDto);
  User findUserByEmailAndPassWord(String email , String password);
}
