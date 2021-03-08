package com.ranktest.creditcardvalidation.models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
public class CreditCardEntity {

    @Id
    private String cardNumber;
    @Column(name = "date_time_added")
    private long dateTimeAdded;
    @Column(name = "country")
    private String country;

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", dateTimeAdded=" + dateTimeAdded +
                ", country='" + country + '\'' +
                '}';
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
