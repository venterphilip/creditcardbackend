package com.ranktest.creditcardvalidation.services.dbservices;

import com.ranktest.creditcardvalidation.models.db.CountryEntity;
import com.ranktest.creditcardvalidation.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountriesService {

    @Autowired
    private CountryRepository repository;

    public List<CountryEntity> getAlCountries(){

        List<CountryEntity> countries = repository.findAll();

        if(countries.size()>0){
            return countries;
        } else {
            return new ArrayList<>();
        }
    }

    public List<CountryEntity> getAllBannedCountries(){

        List<CountryEntity> countries = repository.findAll();

        if(countries.size()>0){

            List<CountryEntity> bannedCountries = new ArrayList<>();

            for (CountryEntity country : countries) {
                if(country.isBanned()){
                    bannedCountries.add(country);
                }
            }

            return bannedCountries;
        } else {
            return new ArrayList<>();
        }

    }

    public CountryEntity updateCountryBannedStatus(CountryEntity entity){

        if(entity.getCountryCode()!=null) {
            Optional<CountryEntity> country = repository.findById(entity.getCountryName());

            if(country.isPresent()){
                CountryEntity newEntity = country.get();
                newEntity.setBanned(entity.isBanned());
                newEntity.setCountryCode(entity.getCountryCode());
                newEntity.setCountryName(entity.getCountryName());
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);
                return entity;
            }

        } else {
            entity = repository.save(entity);
            return entity;
        }
    }

}
