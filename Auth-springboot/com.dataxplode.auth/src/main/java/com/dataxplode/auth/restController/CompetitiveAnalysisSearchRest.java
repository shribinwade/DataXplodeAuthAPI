package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/competititorAnalysisSearch")
public interface CompetitiveAnalysisSearchRest {

        @PostMapping(path = "/addCompetititorAnalysisSearch")
        ResponseEntity<String> addCompetitiveAnalysis(@RequestBody(required = true) Map<String, String> requestMap);

        @PostMapping(path = "/getCompetititorAnalysisSearch")
        ResponseEntity<UserSubscription> getCompetitiveAnalysis(@RequestBody(required = true) Map<String, String> requestMap);

        @DeleteMapping(path = "/deleteCompetititorAnalysis")
        ResponseEntity<UserSubscription> deleteCompetitiveAnalysis(@RequestBody(required = true) Map<String, String> requestMap);

        @GetMapping("/getCompetitorAnalysisSearch")
        ResponseEntity<String> getCompetitorAnalysis(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String competitor);

}
