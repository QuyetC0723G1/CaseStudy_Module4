package com.group4.model;

import com.group4.model.login.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private LocalDate birthDay;
    private String Address;
    private String phoneNumber;
    private String email;
    @OneToOne
    private User user;
    @Column(columnDefinition = "tinyint default 0")
    private boolean deleteFlag;

}
