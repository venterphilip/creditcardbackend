package com.ranktest.creditcardvalidation.datasources;

import com.ranktest.creditcardvalidation.models.card.validation.CardValidationResponse;

public interface RestDataSourceFactory {

    CardValidationResponse getCardValidityFromAPI(String cardNumber);

}
