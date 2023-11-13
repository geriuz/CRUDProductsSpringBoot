package com.crudproducts.crudapiproducts.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class mensajeRespon implements Serializable {
    private  String mensaje;
    private Object object;
}
