package com.ranktest.creditcardvalidation.datasources;

import com.ranktest.creditcardvalidation.models.CreditCard;
import com.ranktest.creditcardvalidation.models.card.validation.CardValidationResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RestDataSourceFactoryUsingMock implements RestDataSourceFactory {

    @Override
    public CardValidationResponse getCardValidityFromAPI(CreditCard card) {
        return null;
    }
}
