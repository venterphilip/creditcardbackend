package com.ranktest.creditcardvalidation.controllers;

import com.ranktest.creditcardvalidation.models.CreditCard;
import com.ranktest.creditcardvalidation.models.db.CreditCardEntity;
import com.ranktest.creditcardvalidation.models.db.CreditCardQueueEntity;
import com.ranktest.creditcardvalidation.services.CardValidationService;
import com.ranktest.creditcardvalidation.services.dbservices.CreditCardQueueService;
import com.ranktest.creditcardvalidation.services.dbservices.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CreditCardsController {

    private static final Logger LOG = LoggerFactory.getLogger(CreditCardsController.class);

    private CardValidationService cardValidationService;
    private CreditCardQueueService queueService;
    private CreditCardService cardService;

    /**
     * Constructor
     * @param cardValidationService
     * @param queueService
     * @param cardService
     */
    @Autowired
    public CreditCardsController(CardValidationService cardValidationService, CreditCardQueueService queueService, CreditCardService cardService) {
        this.cardValidationService = cardValidationService;
        this.queueService = queueService;
        this.cardService = cardService;
    }

    /**
     * Loading of credit card, requires credit card number
     * @param creditCard
     * @returns https status ok or bad request if credit card fails basic validation
     */
    @PostMapping("/card/load")
    public HttpStatus loadCard(@RequestBody CreditCard creditCard) {
        LOG.info(String.format("Incoming request: [%s]",creditCard));

        return cardValidationService.isCardValid(creditCard)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
    }

    /**
     * gets all items in credit card queue
     * @return list of CreditCardQueueEntity
     */
    @GetMapping("/card/queue")
    public List<CreditCardQueueEntity> getCardQueue() {
        LOG.info("get Card queue request");

        return queueService.getAllCreditCardsInQueue();
    }

    /**
     * gets all valid credit cards from mysql db
     * @return list of CreditCardEntity
     */
    @GetMapping("/card")
    public  List<CreditCardEntity> getAllValidCards() {
        LOG.info("get all valid cards request");

        return cardService.getAllCreditCards();
    }
}
