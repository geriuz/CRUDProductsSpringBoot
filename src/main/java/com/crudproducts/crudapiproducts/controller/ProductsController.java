package com.crudproducts.crudapiproducts.controller;


import com.crudproducts.crudapiproducts.model.dto.ProductsDto;
import com.crudproducts.crudapiproducts.model.entity.Products;
import com.crudproducts.crudapiproducts.model.payload.mensajeRespon;
import com.crudproducts.crudapiproducts.service.IProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ProductsController {

    @Autowired
    private IProducts productsService;

    @GetMapping("products")
    public ResponseEntity<?> showAll () {
        List<Products> getProducts = productsService.listAll();

        if(getProducts== null){
            return new ResponseEntity<>(mensajeRespon.builder()
                    .mensaje("no hay registros!!")
                    .object(null)
                    .build()
                    ,HttpStatus.OK);
        }

        return new ResponseEntity<>(mensajeRespon.builder()
                .mensaje("")
                .object(getProducts)
                .build()
                ,HttpStatus.OK);

    }

    @PostMapping("product")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  create (@RequestBody ProductsDto productsDto) {
        Products Productosave = null;
        try {
            Productosave = productsService.save(productsDto);
            return new ResponseEntity<>(mensajeRespon.builder()
                    .mensaje("se ha guardado correctamente")
                    .object(ProductsDto.builder()
                            .id(Productosave.getId())
                            .name(Productosave.getName())
                            .description(Productosave.getDescription())
                            .price(Productosave.getPrice())
                            .image(Productosave.getImage())
                            .build())
                    .build()
                    ,HttpStatus.MOVED_PERMANENTLY);

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(mensajeRespon.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED);
        }


    }

    @PutMapping("product")
    public ResponseEntity<?>  update (@RequestBody ProductsDto productsDto, @PathVariable Long id) {
        Products productsUpdate = null;

        try {


            if (productsService.existsById(id)) {
                productsDto.setId(id);
                productsUpdate = productsService.save(productsDto);
                return new ResponseEntity<>(mensajeRespon.builder()
                        .mensaje("se ha guardado correctamente")
                        .object(productsDto.builder()
                                .id(productsUpdate.getId())
                                .name(productsUpdate.getName())
                                .description(productsUpdate.getDescription())
                                .price(productsUpdate.getPrice())
                                .image(productsUpdate.getImage())
                                .build())
                        , HttpStatus.CREATED);
            }else
                return new ResponseEntity<>(mensajeRespon.builder()
                        .mensaje("el registro que intenta actualizar no se encuentra en la base de datos")
                        .object(null)
                        .build()
                        , HttpStatus.NOT_FOUND );

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(mensajeRespon.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND );
        }

    }

    @DeleteMapping("product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete (@PathVariable Long id) {


        try {

            Products productsDelete = productsService.findById(id);
            productsService.delete(productsDelete);
            return new ResponseEntity<>(productsDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){

            return new ResponseEntity<>(mensajeRespon.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{id}")
    public ResponseEntity<?> showById (@PathVariable Long id) {
        Products products = productsService.findById(id);

        if(products == null){
            return new ResponseEntity<>(mensajeRespon.builder()
                    .mensaje("¡¡el registro que intenta buscar no existe!!")
                    .object(null)
                    .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(mensajeRespon.builder()
                .mensaje("")
                .object(ProductsDto.builder()
                        .id(products.getId())
                        .name(products.getName())
                        .description(products.getDescription())
                        .price(products.getPrice())
                        .image(products.getImage())
                        .build())
                .build()
                ,HttpStatus.OK);

    }
}
