package com.group4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "`order`")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalMoney;
    private LocalDateTime orderTime;
    @ManyToOne
    private Customer customer;
    private int status;
    @Column(columnDefinition = "tinyint default 0")
    private boolean deleteFlag;

}
