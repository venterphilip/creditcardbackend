package com.ranktest.creditcardvalidation.services;

import com.ranktest.creditcardvalidation.datasources.DataSourceFactory;
import com.ranktest.creditcardvalidation.models.CreditCard;
import com.ranktest.creditcardvalidation.models.card.validation.CardValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class CardValidationService {

    private DataSourceFactory dataSourceFactory;
    private static final Logger LOG = LoggerFactory.getLogger(CardValidationService.class);

    @Autowired
    public CardValidationService(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public boolean isCardValid(CreditCard creditCard) {

        if(!basicCardValidation(creditCard.getCardNumber())){
            return false;
        }

        CardValidationResponse response = dataSourceFactory.getRestDataSourceFactory().getCardValidityFromAPI(creditCard);

        LOG.info(String.format("Response from API [%s]", response.toString()));

        return response.getNumber().isLuhn();
    }

    public boolean basicCardValidation(String number){
        if(number.matches("[0-9]+")&&number.length()==16){
            return true;
        } else{
            return false;
        }
    }
}
