package com.igarcia.prueba.tecnica.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {

    private String id;
    private String name;
    private BigDecimal price;
    private Boolean availability;
}
