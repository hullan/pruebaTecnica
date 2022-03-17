package com.igarcia.prueba.tecnica.controller;

import java.util.ArrayList;
import java.util.List;

import com.igarcia.prueba.tecnica.model.ProductResponse;
import com.igarcia.prueba.tecnica.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/product/{productId}/similar", produces = {
            "application/json" }, method = RequestMethod.GET)
    public List<ProductResponse> getProductSimilar(@PathVariable("productId") String productId) {
        List<Integer> products = service.getProductSimilaridsCall(productId);

        List<ProductResponse> response = new ArrayList<>();

        products.forEach(p -> response.add(service.getProductProductIdCall(p + "")));

        return response;
    }
}
