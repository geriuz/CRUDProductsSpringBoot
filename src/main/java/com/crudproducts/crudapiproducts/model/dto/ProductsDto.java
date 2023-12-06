package com.crudproducts.crudapiproducts.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ProductsDto implements Serializable{
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
