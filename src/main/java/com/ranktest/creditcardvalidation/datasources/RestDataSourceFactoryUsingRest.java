package com.ranktest.creditcardvalidation.datasources;

import com.ranktest.creditcardvalidation.models.card.validation.CardValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class RestDataSourceFactoryUsingRest implements RestDataSourceFactory {

    private final RestTemplate template;
    private static final Logger LOG = LoggerFactory.getLogger(RestDataSourceFactoryUsingRest.class);

    @Value("${rest.cardbin.url}")
    private String baseUrl;

    @Autowired
    public RestDataSourceFactoryUsingRest(RestTemplate template) {
        this.template = template;
    }

    /**
     * Rest request to cardbin api
     * @param cardNumber
     * @return CardValidationResponse object
     */
    @Override
    public CardValidationResponse getCardValidityFromAPI(String cardNumber) {
        String url = baseUrl + cardNumber;
        LOG.info(String.format("Calling cardbin for details on card: [%s]", url));
        return template.getForObject(url, CardValidationResponse.class);
    }
}
