package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.UserDto;
import com.gabispa.restfulservice.entity.User;
import com.gabispa.restfulservice.exception.BadRequestException;

import com.gabispa.restfulservice.mapper.userMapper;
import com.gabispa.restfulservice.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public void addUser(User user) throws BadRequestException {
    if (user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getAccountName().isEmpty()) {
      throw new BadRequestException("Field required is not blank.");
    }
    //hash Password
    user.setPassword(user.getPassword());
    userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(Long id) {

      Optional<User> user = userRepository.findById(id);
      if (user.isPresent()) {
        return user.get();
      } else {
        throw new BadRequestException("Email or Password is invalid");
      }


  }

  @Override
  public String deleteUser(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      userRepository.deleteById(userId);
      return "Delete user success";
    } else {
      throw new BadRequestException("Id is invalid");
    }
  }

  @Override
  public void updateStateUser(Long id, UserDto userDto) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setState(userDto.getState());
      userRepository.save(user);
    } else {
      throw new BadRequestException("Id is invalid " + id);
    }
  }


  @Override
  public String updateUser(Long id, UserDto userDto) {

    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      User user=optionalUser.get();
      user = userMapper.toEntity(userDto);
      userRepository.save(user);
      return "Update user success";
    } else {
      throw new BadRequestException("Id is invalid");
    }
  }

  @Override
  public User findUserByEmailAndPassWord(String email, String password) throws BadRequestException {

    User user = userRepository.findUserByEmailAndPassWord(email,password);

    if(user==null){
        throw new BadRequestException("Email or Password is invalid");
    }
    return user;
  }

  public List<User> getUserSer(String accountName,String state){
    List<User> userList =  userRepository.getUser(accountName,state);
    if(userList.size()==0){
      throw new BadRequestException("AccountName or State is invalid");
    }
    return userList;
  }

}



