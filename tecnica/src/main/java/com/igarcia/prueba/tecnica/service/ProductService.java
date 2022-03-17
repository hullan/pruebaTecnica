package com.igarcia.prueba.tecnica.service;

import java.util.List;

import com.igarcia.prueba.tecnica.model.ProductResponse;
import com.igarcia.prueba.tecnica.webclient.ProductWebClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductWebClient productWebClient;

    public ProductResponse getProductProductIdCall(String productId) {
        return productWebClient.getProductProductIdCall(productId);
    }

    public List<Integer> getProductSimilaridsCall(String productId) {
        return productWebClient.getProductSimilaridsCall(productId);
    }
}
