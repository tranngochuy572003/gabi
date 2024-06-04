package com.gabispa.restfulservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")

public class Category extends BaseEntity{

  @OneToOne(mappedBy = "category",cascade = CascadeType.ALL)
  private Supply supply;
  @Column
  private String name ;
  @Column
  private String description ;




}
