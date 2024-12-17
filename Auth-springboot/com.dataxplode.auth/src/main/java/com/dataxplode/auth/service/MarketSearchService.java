package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface MarketSearchService {

    ResponseEntity<String> addMarket(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> deleteMarket( Map<String, String> requestMap);

//    ResponseEntity<UserSubscription> getMarket(@RequestBody(required = true) Map<String, String> requestMap);

    ResponseEntity<String> searchMarket(Integer UserID,  String country, String market);

}
