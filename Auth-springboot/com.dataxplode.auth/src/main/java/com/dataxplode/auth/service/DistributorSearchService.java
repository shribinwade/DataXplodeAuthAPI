package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DistributorSearchService {
    ResponseEntity<String> addDistributor(Map<String, String> requestMap);

//    ResponseEntity<UserSubscription> getDistributor(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> deleteDistributor( Map<String, String> requestMap);

    ResponseEntity<String> getDistributorData(String country, String Distributor, Integer UserID);

}
