package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.UserDto;
import com.gabispa.restfulservice.entity.User;
import com.gabispa.restfulservice.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private IUserRepository IUserRepository;
    private static final String pattern = "dd/MM/yyyy";


    @Autowired
    public userService(com.gabispa.restfulservice.repository.IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }


    @Override
    public void createUser(User user) {

        user.setPassword((user.getPassword()));

        IUserRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return IUserRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> users = IUserRepository.findById(id);
        return users.get();
    }

    @Override
    public String deleteUser(Long userId) {
        IUserRepository.deleteById(userId);
        return "delete user success";
    }

    @Override
    public void updateStateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = IUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setState(userDto.getState());
            IUserRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

    }


    @Override
    public String updateUser(Long id, UserDto userDto) {

        Optional<User> optionalUser = IUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setState(userDto.getState());

            user.setEmail(userDto.getEmail());

            userDto.setPassword((userDto.getPassword()));

            user.setAddress(userDto.getAddress());
            user.setAge(userDto.getAge());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                Date date = simpleDateFormat.parse(userDto.getBirthDay());
                user.setBirthDay(date);

            } catch (ParseException e) {
                return "Update user error because date parse fail ";
            }

            user.setPhone(userDto.getPhone());
            user.setNote(userDto.getNote());
            user.setRole(userDto.getRole());
            IUserRepository.save(user);
            return "Update user success";

        } else {
            return "User not found";

        }
    }

    @Override
    public User findUserByEmailAndPassWord(String email, String password) throws EntityNotFoundException {
        List<User> userList = IUserRepository.findAll();
            for (User user : userList) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            return null;

        }

    }



