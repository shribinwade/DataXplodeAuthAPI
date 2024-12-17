package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/marketSearch")
public interface MarketSearchRest {

        @PostMapping(path = "/addMarketSearch")
        ResponseEntity<String> addMarket(@RequestBody(required = true) Map<String, String> requestMap);

        @DeleteMapping(path = "/deleteMarket")
        ResponseEntity<UserSubscription> deleteMarket(@RequestBody(required = true) Map<String, String> requestMap);

        @PostMapping(path = "/getMarketSearch")
        ResponseEntity<UserSubscription> getMarket(@RequestBody(required = true) Map<String, String> requestMap);

        @GetMapping("/getMarketData")
        ResponseEntity<String> searchMarket(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String market);

}
