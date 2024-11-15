package com.dataxplode.auth.service.PlanService;

import com.dataxplode.auth.wrapper.PlanWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PlanService {

    ResponseEntity<String> createPlan(Map<String, String> requestMap);
    ResponseEntity<String> updatePlan(Map<String, String> requestMap);
    ResponseEntity<String> deletePlan(Map<String, String> requestMap);
    ResponseEntity<List<PlanWrapper>> getAllPlans();
}
