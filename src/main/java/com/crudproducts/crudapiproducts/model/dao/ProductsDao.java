package com.crudproducts.crudapiproducts.model.dao;

import com.crudproducts.crudapiproducts.model.entity.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductsDao extends CrudRepository<Products, Long> {


}
