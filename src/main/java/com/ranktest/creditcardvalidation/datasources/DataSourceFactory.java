package com.ranktest.creditcardvalidation.datasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceFactory {

    private RestDataSourceFactoryUsingMock restDataSourceFactoryUsingMock;
    private RestDataSourceFactoryUsingRest restDataSourceFactoryUsingRest;

    @Autowired
    public DataSourceFactory(RestDataSourceFactoryUsingMock restDataSourceFactoryUsingMock, RestDataSourceFactoryUsingRest restDataSourceFactoryUsingRest) {
        this.restDataSourceFactoryUsingMock = restDataSourceFactoryUsingMock;
        this.restDataSourceFactoryUsingRest = restDataSourceFactoryUsingRest;
    }

    @Value("${rest.mock.enabled}")
    private boolean mockRest;

    @Value("${db.mock.enabled}")
    private boolean mockDB;

    public RestDataSourceFactory getRestDataSourceFactory(){
        if(mockRest){
            return restDataSourceFactoryUsingMock;
        } else {
            return restDataSourceFactoryUsingRest;
        }
    }


}
