package com.crudproducts.crudapiproducts.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "products")
public class Products implements Serializable{
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column (name = "name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "price")
    private Double price;

    @Column (name = "stock")
    private Integer stock;
}
