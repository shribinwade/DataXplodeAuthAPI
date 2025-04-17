package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.CompetitiveAnalysisSearchRest;
import com.dataxplode.auth.service.CompetitveAnalysisService;
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
public class CompetitveAnalysisRestImpl  implements CompetitiveAnalysisSearchRest {

    @Autowired
    CompetitveAnalysisService competitveAnalysisService;

    private static final Logger log = LoggerFactory.getLogger(CompetitveSearchRestImpl.class);

    @Override
    public ResponseEntity<String> addCompetitiveAnalysis(Map<String, String> requestMap) {
        try{
            return competitveAnalysisService.addCompetitiveAnalysis(requestMap);
        }catch (Exception ex){
            log.info("Unable to add CompetitorAnalysis:",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> getCompetitiveAnalysis(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteCompetitiveAnalysis(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> getCompetitorAnalysis(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String competitor){
        try{
            return competitveAnalysisService.getCompetitorAnalysis(UserID, country, competitor );
        }catch (Exception ex){
            log.info("Unable to add Distributor: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

