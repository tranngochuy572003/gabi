package com.gabispa.restfulservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Book extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "supply_id")
  private Supply supply ;

  @Column
  private String staffName ;

}
