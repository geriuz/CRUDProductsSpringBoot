package com.crudproducts.crudapiproducts.service.impl;

import com.crudproducts.crudapiproducts.model.dao.ProductsDao;
import com.crudproducts.crudapiproducts.model.dto.ProductsDto;
import com.crudproducts.crudapiproducts.model.entity.Products;
import com.crudproducts.crudapiproducts.service.IProducts;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsImpl implements IProducts {
    @Autowired
    private ProductsDao productsDao;

    @Override
    public List<Products> listAll() {
        return (List) productsDao.findAll();
    }

    @Transactional
    @Override
    public Products save(ProductsDto productsDto) {
        Products products = Products.builder().id(productsDto.getId())
                .name(productsDto.getName())
                .description(productsDto.getDescription())
                .price(productsDto.getPrice())
                .stock(productsDto.getStock())
                .build();
        return productsDao.save(products);
    }

    @Transactional
    @Override
    public Products findById(Long id) {
        return productsDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Products products) {
        productsDao.delete(products);
    }

    @Override
    public boolean existsById(Long id) {
        return productsDao.existsById(id);
    }
}
