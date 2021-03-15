package com.ranktest.creditcardvalidation.services.dbservices;

import com.ranktest.creditcardvalidation.exceptions.RecordNotFoundException;
import com.ranktest.creditcardvalidation.models.db.CreditCardEntity;
import com.ranktest.creditcardvalidation.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository repository;

    /**
     * gets all valid credit cards from table
     * @return list of credit cards
     */
    public List<CreditCardEntity> getAllCreditCards(){
        List<CreditCardEntity> creditCards = repository.findAll();

        if(creditCards.size()>0){
            return creditCards;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * adds credit card to table
     * @param entity
     * @return credit card added to table
     */
    public CreditCardEntity addCreditCard(CreditCardEntity entity){
        if(entity.getCardNumber()!=null) {
            Optional<CreditCardEntity> creditCard = repository.findById(entity.getCardNumber());

            if(creditCard.isPresent()){
                CreditCardEntity newEntity = creditCard.get();
                newEntity.setCardNumber(entity.getCardNumber());
                newEntity.setDateTimeAdded(System.currentTimeMillis());
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity.setDateTimeAdded(System.currentTimeMillis());
                entity = repository.save(entity);
                return entity;
            }

        } else {
            entity = repository.save(entity);
            return entity;
        }
    }

    /**
     * remove credit card from table
     * @param cardNumber
     */
    public void removeCreditCard(String cardNumber){
        Optional<CreditCardEntity> cardQueueEntity = repository.findById(cardNumber);

        if(cardQueueEntity.isPresent()) {
            repository.deleteById(cardNumber);
        } else {
            throw new RecordNotFoundException("No record exist for given id",cardNumber);
        }
    }

    /**
     * get spesific credit card info
     * @param cardNumber
     * @return credit card entity
     */
    public CreditCardEntity getCreditCard(String cardNumber){
        Optional<CreditCardEntity> cardEntity = repository.findById(cardNumber);

        if(cardEntity.isPresent()) {
            return cardEntity.get();
        } else {
            return null;
        }
    }

}
