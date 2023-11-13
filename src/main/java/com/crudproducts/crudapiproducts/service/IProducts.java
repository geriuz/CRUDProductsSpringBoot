package com.crudproducts.crudapiproducts.service;

import com.crudproducts.crudapiproducts.model.dto.ProductsDto;
import com.crudproducts.crudapiproducts.model.entity.Products;

import java.util.List;

public interface IProducts {

    List<Products> listAll();
    Products save (ProductsDto products);
    Products findById(Long id);
    void delete(Products products);
    boolean existsById(Long id);
}
