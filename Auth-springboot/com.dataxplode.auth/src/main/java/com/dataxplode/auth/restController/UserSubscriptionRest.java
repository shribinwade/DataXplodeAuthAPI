package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/subscription")
public interface UserSubscriptionRest {
    @PostMapping(path = "/createSubscription")
    ResponseEntity<String> createSubscription(User user);

    @PostMapping(path = "/getUserSubscription")
    ResponseEntity<UserSubscription> getUsersSubscription(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/upgradeSubscription")
    ResponseEntity<UserSubscription> upgradeSubscription(@RequestBody(required = true) Map<String, String> requestMap);


}
