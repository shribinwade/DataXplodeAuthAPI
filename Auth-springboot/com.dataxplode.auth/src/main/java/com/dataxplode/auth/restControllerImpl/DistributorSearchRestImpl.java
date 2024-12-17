package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.DistributorSearchRest;
import com.dataxplode.auth.service.DistributorSearchService;
import com.dataxplode.auth.serviceImpl.PlatformServiceImpl;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DistributorSearchRestImpl implements DistributorSearchRest {

    @Autowired
    DistributorSearchService distributorSearchService;

    private static final Logger log = LoggerFactory.getLogger(DistributorSearchRestImpl.class);

    @Override
    public ResponseEntity<String> addDistributor(Map<String, String> requestMap) {
        try{
            return distributorSearchService.addDistributor( requestMap);
        }catch (Exception ex){
            log.info("Unable to add Keyword: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> getDistributor(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> searchDistributor(Integer UserID, String country, String Distributor) {
        try{
            return distributorSearchService.getDistributorData(country, Distributor, UserID );
        }catch (Exception ex){
            log.info("Unable to add Distributor: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> deleteDistributor(Map<String, String> requestMap) {
        return null;
    }
}
