package com.gabispa.restfulservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class baseEntity {

  @Id
  @Column(name ="id" , insertable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name="createdDate")
  @CreationTimestamp
  private Date createdDate ;

  @Column(name="createdBy")
  @CreatedBy
  private String createdBy ;

  @Column(name="modifiedBy")
  @LastModifiedBy
  private String modifiedBy ;

  @Column(name="modifiedDate")
  @UpdateTimestamp
  private Date modifiedDate ;

  @Column(name="state")
  private String state;




}
