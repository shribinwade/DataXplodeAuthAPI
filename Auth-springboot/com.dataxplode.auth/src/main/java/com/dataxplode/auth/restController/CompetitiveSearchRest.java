package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/competitiveSearch")
public interface CompetitiveSearchRest {

        @PostMapping(path = "/addCompetitiveSearch")
        ResponseEntity<String> addCompetitive(@RequestBody(required = true) Map<String, String> requestMap);

//        @PostMapping(path = "/getCompetitiveSearch")
//        ResponseEntity<UserSubscription> getCompetitive(@RequestBody(required = true) Map<String, String> requestMap);

        @DeleteMapping(path = "/deleteCompetitive")
        ResponseEntity<UserSubscription> deleteCompetitive(@RequestBody(required = true) Map<String, String> requestMap);

        @GetMapping("/getCompetitiveSearch")
        ResponseEntity<String> getCompetitive(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String competitive);

}
