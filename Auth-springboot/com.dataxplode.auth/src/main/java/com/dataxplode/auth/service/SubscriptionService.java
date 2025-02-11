package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;

import com.dataxplode.auth.wrapper.UserSearchDataWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SubscriptionService {
    UserSubscription createSubscription(User user);

    ResponseEntity<UserSubscription> getUsersSubscription(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> upgradeSubscription(Map<String, String> requestMap);

    ResponseEntity<List<UserSearchDataWrapper>>getUserSearchData(Integer UserID, String country, String platform);

    boolean isValid(UserSubscription userSubscription);
    
}
