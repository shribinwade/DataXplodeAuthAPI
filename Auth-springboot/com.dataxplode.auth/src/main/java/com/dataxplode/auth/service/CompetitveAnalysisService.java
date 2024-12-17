package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface CompetitveAnalysisService {

    ResponseEntity<String> addCompetitiveAnalysis(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> getCompetitiveAnalysis(Map<String, String> requestMap);

    ResponseEntity<UserSubscription> deleteCompetitiveAnalysis( Map<String, String> requestMap);

    ResponseEntity<String> getCompetitorAnalysis(Integer UserID, String country,String competitor);

}
