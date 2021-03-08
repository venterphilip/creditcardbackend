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

    public List<CreditCardEntity> getAllCreditCards(){
        List<CreditCardEntity> creditCards = repository.findAll();

        if(creditCards.size()>0){
            return creditCards;
        } else {
            return new ArrayList<>();
        }
    }

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

    public void removeCreditCard(String cardNumber){
        Optional<CreditCardEntity> cardQueueEntity = repository.findById(cardNumber);

        if(cardQueueEntity.isPresent()) {
            repository.deleteById(cardNumber);
        } else {
            throw new RecordNotFoundException("No record exist for given id",cardNumber);
        }
    }

    public CreditCardEntity getCreditCard(String cardNumber){
        Optional<CreditCardEntity> cardEntity = repository.findById(cardNumber);

        if(cardEntity.isPresent()) {
            return cardEntity.get();
        } else {
            return null;
        }
    }

}
