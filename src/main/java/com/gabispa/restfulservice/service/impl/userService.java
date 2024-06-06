package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.userDto;
import com.gabispa.restfulservice.entity.User;
import com.gabispa.restfulservice.exception.accountNotFound;
import com.gabispa.restfulservice.exception.idNotFound;
import com.gabispa.restfulservice.exception.invalidFieldException;
import com.gabispa.restfulservice.exception.listNullException;
import com.gabispa.restfulservice.mapper.userMapper;
import com.gabispa.restfulservice.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Data
@NoArgsConstructor
public class userService implements com.gabispa.restfulservice.service.userService {
  private userRepository userRepository;


  @Autowired
  public userService(userRepository IUserRepository) {
    this.userRepository = IUserRepository;
  }

  @Override
  public void addUser(User user) throws invalidFieldException {
    if (user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getAccountName().isEmpty()) {
      throw new invalidFieldException("Field required is not blank.");
    }
    //hash Password
    user.setPassword(user.getPassword());
    userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    List<User> userList = userRepository.findAll();
    if (userList.isEmpty()) {
      throw new listNullException("List empty");
    }
    return userList;
  }

  @Override
  public User getUserById(Long id) {

    try {
      Optional<User> users = userRepository.findById(id);
      if (users.isPresent()) {
        return users.get();
      } else {
        throw new listNullException("List User is null");
      }
    } catch (Exception e) {
      throw new idNotFound("Id isn't exist");
    }

  }

  @Override
  public String deleteUser(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      userRepository.deleteById(userId);
      return "Delete user success";
    } else {
      throw new idNotFound("Id isn't exist");
    }
  }

  @Override
  public void updateStateUser(Long id, userDto userDto) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setState(userDto.getState());
      userRepository.save(user);
    } else {
      throw new EntityNotFoundException("User not found with id: " + id);
    }
  }


  @Override
  public String updateUser(Long id, userDto userDto) {

    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      User user=optionalUser.get();
      user = userMapper.toEntity(userDto);
      userRepository.save(user);
      return "Update user success";
    } else {
      throw new idNotFound("User not found");
    }
  }

  @Override
  public User findUserByEmailAndPassWord(String email, String password) throws EntityNotFoundException {
    List<User> userList = userRepository.findAll();
    User User = new User();
    if (userList.isEmpty()) {
      throw new listNullException("userList return null");

    }
    for (User user : userList) {
      if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
        return user;
      }
      else {
        throw new accountNotFound("UserName or PassWord isn't not valid");
      }
    }
    return User;
  }
}



