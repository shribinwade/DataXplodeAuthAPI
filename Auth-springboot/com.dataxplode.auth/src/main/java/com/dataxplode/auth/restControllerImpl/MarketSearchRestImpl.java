package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.DistributorSearchRest;
import com.dataxplode.auth.restController.MarketSearchRest;
import com.dataxplode.auth.service.DistributorSearchService;
import com.dataxplode.auth.service.MarketSearchService;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MarketSearchRestImpl implements MarketSearchRest {

    @Autowired
    MarketSearchService marketSearchService;

    private static final Logger log = LoggerFactory.getLogger(MarketSearchRestImpl.class);

    @Override
    public ResponseEntity<String> addMarket(Map<String, String> requestMap) {
        try{
            return marketSearchService.addMarket( requestMap);
        }catch (Exception ex){
            log.info("Unable to add Keyword: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> getMarket(Map<String, String> requestMap) {
        return null;
    }


    @Override
    public ResponseEntity<String> searchMarket(Integer UserID, String country, String market) {
        try{
            return marketSearchService.searchMarket(UserID, country, market);
        }catch (Exception ex){
            log.info("Unable to add Market: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> deleteMarket(Map<String, String> requestMap) {
        return null;
    }
}
