package com.group4.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "product_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    @Column(columnDefinition = "tinyint default 0")
    private boolean deleteFlag;
}
