package com.ranktest.creditcardvalidation.models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards_queue")
public class CreditCardQueueEntity {

    @Id
    private String cardNumber;
    @Column(name="date_time_added")
    private long dateTimeAdded;

    @Override
    public String toString() {
        return "CreditCardQueueEntity{" +
                "cardNumber='" + cardNumber + '\'' +
                ", dateTimeAdded=" + dateTimeAdded +
                '}';
    }

    public CreditCardQueueEntity() {
    }

    public CreditCardQueueEntity(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getDateTimeAdded() {
        return dateTimeAdded;
    }

    public void setDateTimeAdded(long dateTimeAdded) {
        this.dateTimeAdded = dateTimeAdded;
    }

}
