package com.gabispa.restfulservice.repository;

import com.gabispa.restfulservice.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface supplyRepository extends JpaRepository<Supply,Long> {

}
