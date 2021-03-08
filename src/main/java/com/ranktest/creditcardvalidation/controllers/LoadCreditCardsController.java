package com.ranktest.creditcardvalidation.controllers;

import com.ranktest.creditcardvalidation.models.CreditCard;
import com.ranktest.creditcardvalidation.services.CardValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadCreditCardsController {

    private static final Logger LOG = LoggerFactory.getLogger(LoadCreditCardsController.class);

    private CardValidationService cardValidationService;

    public LoadCreditCardsController(CardValidationService cardValidationService) {
        this.cardValidationService = cardValidationService;
    }

    @PostMapping("/load/card")
    public String index(@RequestBody CreditCard creditCard) {
        LOG.info(String.format("Incoming request: [%s]",creditCard));



        return "is card valid: " + cardValidationService.isCardValid(creditCard);
    }


}
