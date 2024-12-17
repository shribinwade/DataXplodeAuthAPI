package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/distributorSearch")
public interface DistributorSearchRest {

        @PostMapping(path = "/addDistributorSearch")
        ResponseEntity<String> addDistributor(@RequestBody(required = true) Map<String, String> requestMap);

        @DeleteMapping(path = "/deleteDistributor")
        ResponseEntity<UserSubscription> deleteDistributor(@RequestBody(required = true) Map<String, String> requestMap);

        @PostMapping(path = "/getDistributorSearch")
        ResponseEntity<UserSubscription> getDistributor(@RequestBody(required = true) Map<String, String> requestMap);

        @GetMapping("/getDistributorData")
        ResponseEntity<String> searchDistributor(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String Distributor);

}
