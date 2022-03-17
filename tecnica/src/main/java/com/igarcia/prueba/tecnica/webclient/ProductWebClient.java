package com.igarcia.prueba.tecnica.webclient;

import java.util.Arrays;
import java.util.List;

import com.igarcia.prueba.tecnica.model.ProductResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductWebClient {
    private static final String URL = "http://localhost:3001/product/";

    public ProductResponse getProductProductIdCall(String productId) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate
                .getForObject(URL + productId, ProductResponse.class);
    }

    public List<Integer> getProductSimilaridsCall(String productId) {
        RestTemplate restTemplate = new RestTemplate();

        return Arrays.asList(restTemplate.getForEntity(
                URL + productId + "/similarids",
                Integer[].class).getBody());
    }
}
