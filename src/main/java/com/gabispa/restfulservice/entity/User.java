package com.gabispa.restfulservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User extends baseEntity {

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Book> bookings;

  @Column
  private String accountName;
  @Column
  private String email;
  @Column
  private String password;
  @Column
  private String address;
  @Column
  private Date birthDay;
  @Column
  private Long age;
  @Column
  private String phone;
  @Column
  private String note;
  @Column
  private String role;
  @Column
  private String state ;

}
