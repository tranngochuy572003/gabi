package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.userDto;
import com.gabispa.restfulservice.dto.userDtoLogin;
import com.gabispa.restfulservice.entity.User;
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
public class userController {
  @Autowired
  private com.gabispa.restfulservice.service.impl.userService userService;
  private JSONObject payload = new JSONObject();
  private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";


  private static String encode(byte[] bytes) {
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }

  private String hmacSha256(String data, String secret) {
    try {
      byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
      Mac sha256Hmac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
      sha256Hmac.init(secretKey);
      byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

      return encode(signedBytes);
    } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
      Logger.getLogger(userController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }


  @PostMapping("/login")
  public ResponseEntity<?> userLogin(@RequestBody userDtoLogin userDtoLogin) {
    try {
      User user = userService.findUserByEmailAndPassWord(userDtoLogin.getEmail(), userDtoLogin.getPassword());
      LocalDateTime ldt = LocalDateTime.now().plusHours(1);

      payload.put("userName", user.getEmail());
      payload.put("role", user.getRole());
      payload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC));

      String secret = "helo";
      String signature = hmacSha256(encode(JWT_HEADER.getBytes()) + "." + encode(payload.toJSONString().getBytes()), secret);
      String jwtToken = encode(JWT_HEADER.getBytes()) + "." + encode(payload.toJSONString().getBytes()) + "." + signature;

      return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }  catch (Exception e) {
      return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<String> addUser(@RequestBody User user) {
    userService.addUser(user);
    return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);

  }

  @GetMapping
  public ResponseEntity<List<User>> userList() {
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);

  }

  @GetMapping("/get/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
      User user = userService.getUserById(id);
      return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PatchMapping("/updateState/{id}")
  public ResponseEntity<String> updateStateUser(@PathVariable Long id, @RequestBody userDto userDto) {
      userService.updateStateUser(id, userDto);
      return new ResponseEntity<>("User state updated successfully", HttpStatus.OK);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody userDto userDto) {
      userService.updateUser(id, userDto);
      return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
      String message = userService.deleteUser(id);
      return new ResponseEntity<>(message, HttpStatus.OK);
  }


}
