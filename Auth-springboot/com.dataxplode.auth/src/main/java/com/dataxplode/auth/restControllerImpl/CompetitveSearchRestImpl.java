package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.CompetitiveSearchRest;
import com.dataxplode.auth.restController.DistributorSearchRest;
import com.dataxplode.auth.service.CompetitorStrategySearchService;
import com.dataxplode.auth.service.DistributorSearchService;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CompetitveSearchRestImpl implements CompetitiveSearchRest {

    @Autowired
    CompetitorStrategySearchService competitorStrategySearchService;

    private static final Logger log = LoggerFactory.getLogger(CompetitveSearchRestImpl.class);

    @Override
    public ResponseEntity<String> addCompetitive(Map<String, String> requestMap) {
        try{
            return competitorStrategySearchService.addCompetitor(requestMap);
        }catch (Exception ex){
            log.info("Unable to add Competititor Data:",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getCompetitive(Integer UserID, String country, String competitive){

        try{
            return competitorStrategySearchService.getCompetitive(UserID, country, competitive );
        }catch (Exception ex){
            log.info("Unable to add Distributor: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<UserSubscription> deleteCompetitive(Map<String, String> requestMap) {
        return null;
    }

}
