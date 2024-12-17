package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface KeywordSearchService {
    ResponseEntity<String> addKeyword(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> getKeyword(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> deleteKeyword( Map<String, String> requestMap);

    ResponseEntity<String> getKeywordData(String country,String keyword ,Integer UserID);

}
