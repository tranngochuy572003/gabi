package com.gabispa.restfulservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "task")

public class Task extends BaseEntity {

  @OneToOne(mappedBy = "task",cascade = CascadeType.ALL)
  private Supply supply;

  @Column
  private String name ;
  @Column
  private String description ;
  @Column
  private String type;
  @Column
  private Long timeWork ;
}
