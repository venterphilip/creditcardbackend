package com.ranktest.creditcardvalidation.controllers;

import com.ranktest.creditcardvalidation.models.db.CountryEntity;
import com.ranktest.creditcardvalidation.services.dbservices.CountriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountriesController {

    private static final Logger LOG = LoggerFactory.getLogger(CountriesController.class);

    private CountriesService service;

    @Autowired
    public CountriesController(CountriesService countriesService){
        this.service = countriesService;
    }

    @PostMapping("/country/ban/{countryCode}/{banValue}")
    public List<CountryEntity> loadCard(@PathVariable("countryCode") String countryCode, @PathVariable("banValue") boolean banValue) {
        LOG.info(String.format("Request to change [%S] to value [%s]",countryCode, banValue));

        CountryEntity entity = new CountryEntity();
        entity.setCountryCode(countryCode);
        entity.setBanned(banValue);

        service.updateCountryBannedStatus(entity);

        return service.getAlCountries();
    }

    @GetMapping("/country")
    public List<CountryEntity> getCardQueue() {
        LOG.info("get Card queue request");

        return service.getAlCountries();
    }

}
