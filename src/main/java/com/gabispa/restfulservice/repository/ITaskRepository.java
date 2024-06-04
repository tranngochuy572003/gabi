package com.gabispa.restfulservice.repository;

import com.gabispa.restfulservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends JpaRepository<Task,Long> {
}
