package com.ranktest.creditcardvalidation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getCountries() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/country", String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void banCountry() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/country/ban/ZA/true", String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }
}
