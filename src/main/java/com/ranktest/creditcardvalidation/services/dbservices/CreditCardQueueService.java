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

    /**
     * gets all credit cards from queue table
     * @return list of credit cards in queue
     */
    public List<CreditCardQueueEntity> getAllCreditCardsInQueue(){
        List<CreditCardQueueEntity> creditCards = repository.findAll();

        if(creditCards.size()>0){
            return creditCards;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * filters to get 5 cards in queue
     * @return list of 5 cards in queue table
     */
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

    /**
     * adds credit card to queue
     * @param entity
     * @return credit card entity
     */
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

    /**
     * remove card from queue table
     * @param cardNumber
     */
    public void removeCreditCardQueueItem(String cardNumber){
        Optional<CreditCardQueueEntity> cardQueueEntity = repository.findById(cardNumber);

        if(cardQueueEntity.isPresent()) {
            repository.deleteById(cardNumber);
        } else {
            throw new RecordNotFoundException("No student record exist for given id",cardNumber);
        }
    }

    /**
     * get spesific card in table
     * @param cardNumber
     * @return single credit card
     */
    public CreditCardQueueEntity getCreditCard(String cardNumber){
        Optional<CreditCardQueueEntity> cardQueueEntity = repository.findById(cardNumber);

        if(cardQueueEntity.isPresent()) {
            return cardQueueEntity.get();
        } else {
            return null;
        }
    }

}
