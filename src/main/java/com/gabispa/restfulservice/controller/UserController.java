package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.UserDto;
import com.gabispa.restfulservice.dto.UserDtoLogin;
import com.gabispa.restfulservice.entity.User;
import com.gabispa.restfulservice.service.impl.UserService;
import jakarta.persistence.EntityNotFoundException;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  private JSONObject payload = new JSONObject();
  private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
  // "alg" : "H256"
  //"type" :"JWT"

  private static String encode(byte[] bytes) {
    // thay thế các ký tự + và / bằng -và _
    // loại bỏ kí tự đệm
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }

  private String hmacSha256(String data, String secret) {
    try {
      // Chuyển đổi khóa bí mật từ chuỗi sang mảng byte
      byte[] hash = secret.getBytes(StandardCharsets.UTF_8);

      // Tạo đối tượng Mac với thuật toán HmacSHA256
      Mac sha256Hmac = Mac.getInstance("HmacSHA256");

      // Tạo đối tượng SecretKeySpec với khóa bí mật
      SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");

      // Khởi tạo đối tượng Mac với khóa bí mật
      sha256Hmac.init(secretKey);

      // Tạo mã HMAC cho dữ liệu đầu vào
      byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

      return encode(signedBytes);
    } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
      Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }


  @PostMapping("/login")
  public ResponseEntity<?> userLogin(@RequestBody UserDtoLogin userDtoLogin) {
    try {
      User user = userService.findUserByEmailAndPassWord(userDtoLogin.getEmail(), userDtoLogin.getPassword());
      LocalDateTime ldt = LocalDateTime.now().plusHours(1); // Token expiration time

      payload.put("userName", user.getEmail());
      payload.put("role", user.getRole());
      payload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC));

      String secret = "helo"; // Replace with your actual secret key
      String signature = hmacSha256(encode(JWT_HEADER.getBytes()) + "." + encode(payload.toJSONString().getBytes()), secret);
      String jwtToken = encode(JWT_HEADER.getBytes()) + "." + encode(payload.toJSONString().getBytes()) + "." + signature;

      return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
      return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);

//      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<String> addUser(@RequestBody User user) {
    try {
      userService.createUser(user);
      return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<List<User>> userList() {
    try {
      List<User> users = userService.getAllUsers();
      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    try {
      User user = userService.getUserById(id);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<String> updateStateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
    try {
      userService.updateStateUser(id, userDto);
      return new ResponseEntity<>("User state updated successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    try {
      String message = userService.deleteUser(id);
      return new ResponseEntity<>(message, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
