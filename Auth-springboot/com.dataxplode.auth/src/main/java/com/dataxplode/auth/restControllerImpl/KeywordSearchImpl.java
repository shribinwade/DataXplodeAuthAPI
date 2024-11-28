package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.Models.featuresModels.KeywordSearchData;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.KeywordSearch;
import com.dataxplode.auth.service.KeywordSearchService;
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
public class KeywordSearchImpl implements KeywordSearch {

    @Autowired
    KeywordSearchService keywordSearchService;

    private static final Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);

    @Override
    public ResponseEntity<String> addKeyword(Map<String, String> requestMap) {
        try{
            return keywordSearchService.addKeyword( requestMap);
        }catch (Exception ex){
            log.info("Unable to add Keyword: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> getKeyword(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteKeyword(Map<String, String> requestMap) {
        return null;
    }
}
