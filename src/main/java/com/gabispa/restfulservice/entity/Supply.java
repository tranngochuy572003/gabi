package com.gabispa.restfulservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "supply")
public class Supply extends baseEntity {

    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL)
    private List<Book> bookings ;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task ;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category ;

    @Column
    private String nameService ;
    @Column
    private Long priceService ;
    @Column
    private String description ;
    @Column
    private String image ;
    @Column
    private String rate ;
    @Column
    private Long intervalDay;
    @Column
    private Long totalDay;





}
