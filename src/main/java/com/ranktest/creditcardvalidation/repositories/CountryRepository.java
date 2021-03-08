package com.ranktest.creditcardvalidation.repositories;

import com.ranktest.creditcardvalidation.models.db.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {
}
