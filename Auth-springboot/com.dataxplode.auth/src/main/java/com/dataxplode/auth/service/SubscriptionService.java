package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SubscriptionService {
    UserSubscription createSubscription(User user);

    ResponseEntity<UserSubscription> getUsersSubscription(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> upgradeSubscription(Map<String, String> requestMap);

    boolean isValid(UserSubscription userSubscription);
}
