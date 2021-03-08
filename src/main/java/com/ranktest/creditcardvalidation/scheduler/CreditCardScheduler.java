package com.ranktest.creditcardvalidation.scheduler;

import com.ranktest.creditcardvalidation.datasources.DataSourceFactory;
import com.ranktest.creditcardvalidation.models.card.validation.CardValidationResponse;
import com.ranktest.creditcardvalidation.models.db.CountryEntity;
import com.ranktest.creditcardvalidation.models.db.CreditCardQueueEntity;
import com.ranktest.creditcardvalidation.services.dbservices.CountriesService;
import com.ranktest.creditcardvalidation.services.dbservices.CreditCardQueueService;
import com.ranktest.creditcardvalidation.services.dbservices.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class CreditCardScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(CreditCardScheduler.class);
    private CreditCardQueueService creditCardQueueService;
    private CreditCardService creditCardService;
    private CountriesService countriesService;
    private DataSourceFactory dataSourceFactory;

    @Autowired
    public CreditCardScheduler(CreditCardQueueService creditCardQueueService, CreditCardService creditCardService, CountriesService countriesService, DataSourceFactory dataSourceFactory) {
        this.creditCardQueueService = creditCardQueueService;
        this.creditCardService = creditCardService;
        this.countriesService = countriesService;
        this.dataSourceFactory = dataSourceFactory;
    }

    @Scheduled(fixedRate = 30000)
    public void scheduledCreditCardBatchJob() {
        LOG.info(String.format("Credit card Batch every 30 seconds: [%d]",System.currentTimeMillis() / 1000));

        List<CreditCardQueueEntity> creditCardQueueEntities = creditCardQueueService.getTopFiveCardsInQueue();

        if(creditCardQueueEntities.size()>0){
            handleBatchJob(creditCardQueueEntities);
        } else{
            LOG.info("Queue is empty!");
        }
    }

    private void handleBatchJob(List<CreditCardQueueEntity> creditCardQueueEntities){
        List<CountryEntity> bannedCountries = countriesService.getAllBannedCountries();

        for (CreditCardQueueEntity item : creditCardQueueEntities){
            CardValidationResponse response = dataSourceFactory.getRestDataSourceFactory().getCardValidityFromAPI(item.getCardNumber());
            LOG.info(String.format("Response from server: [%s]", response.toString() ));

            //if(Banned)
        }
    }
}
