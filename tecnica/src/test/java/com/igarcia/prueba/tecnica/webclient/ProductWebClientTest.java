package com.igarcia.prueba.tecnica.webclient;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.igarcia.prueba.tecnica.model.ProductResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest
public class ProductWebClientTest {

    @Autowired
    private ProductWebClient productWebClient;

    private ClientAndServer mockServer;

    @BeforeEach
    public void startServer() {
        mockServer = ClientAndServer.startClientAndServer(9988);
    }

    @AfterEach
    public void stopServer() {
        mockServer.stop();
    }

    @Test
    void getProductProductIdCall() {
        mockServer.when(HttpRequest.request().withMethod("GET").withPath("product/100")).respond(HttpResponse.response()
                .withBody("{ \"id\": \"100\", \"name\": \"Trousers\", \"price\": 49.99, \"availability\": false}")
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withDelay(TimeUnit.MILLISECONDS, 1000));

        ProductResponse response = productWebClient.getProductProductIdCall("100");

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("100");
        assertThat(response.getName()).isEqualTo("Trousers");
        assertThat(response.getPrice().setScale(2, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal(49.99)
                .setScale(2, RoundingMode.HALF_UP));
        assertThat(response.getAvailability()).isFalse();
    }

    @Test
    void getProductSimilaridsCall() {
        mockServer.when(HttpRequest.request().withMethod("GET").withPath("product/1/similarids"))
                .respond(HttpResponse.response()
                        .withBody("[2,3,4]")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withDelay(TimeUnit.MILLISECONDS, 1000));

        List<Integer> response = productWebClient.getProductSimilaridsCall("1");

        assertThat(response).isNotNull();
        assertThat(response.get(0)).isEqualTo(2);
        assertThat(response.get(1)).isEqualTo(3);
        assertThat(response.get(2)).isEqualTo(4);
    }
}
