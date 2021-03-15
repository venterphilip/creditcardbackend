package com.ranktest.creditcardvalidation.services;

import com.ranktest.creditcardvalidation.datasources.DataSourceFactory;
import com.ranktest.creditcardvalidation.models.CreditCard;
import com.ranktest.creditcardvalidation.models.db.CreditCardEntity;
import com.ranktest.creditcardvalidation.models.db.CreditCardQueueEntity;
import com.ranktest.creditcardvalidation.services.dbservices.CreditCardQueueService;
import com.ranktest.creditcardvalidation.services.dbservices.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class CardValidationService {

    private DataSourceFactory dataSourceFactory;
    private CreditCardService creditCardService;
    private CreditCardQueueService creditCardQueueService;
    private static final Logger LOG = LoggerFactory.getLogger(CardValidationService.class);

    /**
     * Contructor
     * @param dataSourceFactory
     * @param creditCardService
     * @param creditCardQueueService
     */
    @Autowired
    public CardValidationService(DataSourceFactory dataSourceFactory, CreditCardService creditCardService, CreditCardQueueService creditCardQueueService) {
        this.dataSourceFactory = dataSourceFactory;
        this.creditCardService = creditCardService;
        this.creditCardQueueService = creditCardQueueService;
    }

    /**
     * checks basic validation then adds credit card to queue
     * @param creditCard
     * @return if it has been added to queue
     */
    public boolean isCardValid(CreditCard creditCard) {

        if(!basicCardValidation(creditCard.getCardNumber())){
            LOG.info("Failed basic validation");
            return false;
        }

        CreditCardEntity dbEntity = creditCardService.getCreditCard(creditCard.getCardNumber());
        CreditCardQueueEntity dbQueueEntity = creditCardQueueService.getCreditCard(creditCard.getCardNumber());


        if(dbEntity==null&&dbQueueEntity==null){
            CreditCardQueueEntity newQueueItem = new CreditCardQueueEntity(creditCard.getCardNumber());
            creditCardQueueService.addCreditCardQueueItem(newQueueItem);
            return true;
        }

        return false;
    }

    /**
     * basic validation
     * @param number
     * @return boolean if it is only a number and if length is 16
     */
    public boolean basicCardValidation(String number){
        if(number.matches("[0-9]+")&&number.length()==16){
            return true;
        } else{
            return false;
        }
    }
}
