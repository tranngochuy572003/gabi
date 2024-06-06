package com.gabispa.restfulservice.repository;

import com.gabispa.restfulservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Long> {

  @Query(value = "SELECT * FROM User us WHERE us.account_name = ?1 AND us.state = ?2", nativeQuery = true)
  public List<User> getUser(String accountName, String state);


  @Query("SELECT us from User us WHERE us.email=?1 and us.password=?2")
  public User findUserByEmailAndPassWord(String email, String password);
}
