package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface CompetitorStrategySearchService {
    ResponseEntity<String> addCompetitor(Map<String, String> requestMap);

//    ResponseEntity<UserSubscription> getCompetitor(Map<String, String> requestMap);
    ResponseEntity<String> getCompetitive(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String competitive);


    ResponseEntity<UserSubscription> deleteCompetitor( Map<String, String> requestMap);
}
