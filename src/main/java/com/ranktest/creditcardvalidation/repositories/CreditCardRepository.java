package com.ranktest.creditcardvalidation.repositories;

import com.ranktest.creditcardvalidation.models.db.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, String> {

}
