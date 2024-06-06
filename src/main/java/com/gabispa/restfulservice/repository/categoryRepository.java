package com.gabispa.restfulservice.repository;

import com.gabispa.restfulservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category,Long> {
}
