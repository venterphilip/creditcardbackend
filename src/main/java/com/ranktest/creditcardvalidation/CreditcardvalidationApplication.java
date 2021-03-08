package com.ranktest.creditcardvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CreditcardvalidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditcardvalidationApplication.class, args);
    }

}
