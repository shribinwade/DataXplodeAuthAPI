package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.wrapper.UserSearchDataWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/subscription")
public interface UserSubscriptionRest {
    @PostMapping(path = "/createSubscription")
    ResponseEntity<String> createSubscription(User user);

    @PostMapping(path = "/getUserSubscription")
    ResponseEntity<UserSubscription> getUsersSubscription(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/upgradeSubscription")
    ResponseEntity<UserSubscription> upgradeSubscription(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path ="/getUserSearchData")
    ResponseEntity<List<UserSearchDataWrapper>>getUserSearchData(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String platform);


}
