package com.ranktest.creditcardvalidation.repositories;

import com.ranktest.creditcardvalidation.models.db.CreditCardQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardQueueRepository extends JpaRepository<CreditCardQueueEntity, String> {
}
