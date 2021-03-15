package com.ranktest.creditcardvalidation;

import com.ranktest.creditcardvalidation.models.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTests {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getCardQueue() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/card/queue", String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void getCards() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/card", String.class);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void loadCards() {
        CreditCard card = new CreditCard();
        card.setCardNumber("4654645446544564");
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/card/load", card, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
