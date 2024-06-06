package com.gabispa.restfulservice.repository;

import com.gabispa.restfulservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User,Long> {

}
