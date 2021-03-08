package com.ranktest.creditcardvalidation.services.dbservices;

import com.ranktest.creditcardvalidation.exceptions.RecordNotFoundException;
import com.ranktest.creditcardvalidation.models.db.CreditCardQueueEntity;
import com.ranktest.creditcardvalidation.repositories.CreditCardQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreditCardQueueService {

    @Autowired
    private CreditCardQueueRepository repository;

    public List<CreditCardQueueEntity> getAllCreditCardsInQueue(){
        List<CreditCardQueueEntity> creditCards = repository.findAll();

        if(creditCards.size()>0){
            return creditCards;
        } else {
            return new ArrayList<>();
        }
    }

    public List<CreditCardQueueEntity> getTopFiveCardsInQueue(){
        List<CreditCardQueueEntity> creditCards = repository.findAll();

        if(creditCards.size()>0){

            Collections.sort(creditCards, new Comparator<CreditCardQueueEntity>() {

                @Override
                public int compare(CreditCardQueueEntity o1, CreditCardQueueEntity o2) {
                    return o2.getDateTimeAdded() > o1.getDateTimeAdded()? 1 : 0 ;
                }
            });

            if(creditCards.size()>5){
                return creditCards.subList(0,5);
            } else {
                return creditCards.subList(0,creditCards.size());
            }
        } else {
            return new ArrayList<>();
        }
    }

    public CreditCardQueueEntity addCreditCardQueueItem(CreditCardQueueEntity entity){
        if(entity.getCardNumber()!=null) {
            Optional<CreditCardQueueEntity> creditCardQueueEntity = repository.findById(entity.getCardNumber());

            if(creditCardQueueEntity.isPresent()){
                CreditCardQueueEntity newEntity = creditCardQueueEntity.get();
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

    public void removeCreditCardQueueItem(String cardNumber){
        Optional<CreditCardQueueEntity> cardQueueEntity = repository.findById(cardNumber);

        if(cardQueueEntity.isPresent()) {
            repository.deleteById(cardNumber);
        } else {
            throw new RecordNotFoundException("No student record exist for given id",cardNumber);
        }
    }

    public CreditCardQueueEntity getCreditCard(String cardNumber){
        Optional<CreditCardQueueEntity> cardQueueEntity = repository.findById(cardNumber);

        if(cardQueueEntity.isPresent()) {
            return cardQueueEntity.get();
        } else {
            return null;
        }
    }

}
