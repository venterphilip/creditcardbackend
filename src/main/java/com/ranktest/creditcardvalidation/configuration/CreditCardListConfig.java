package com.ranktest.creditcardvalidation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreditCardListConfig {

    @Bean
    @Scope(value="singleton")
    public List<String> creditCardList(){
        return new ArrayList<>();
    }

}
