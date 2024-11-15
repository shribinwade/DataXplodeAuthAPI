package com.dataxplode.auth.restController;

import com.dataxplode.auth.wrapper.PlanWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/plan")
public interface PlanRest {

    //To-Do
    //Create plan
    @PostMapping(path="/create")
    public ResponseEntity<String> createPlan(@RequestBody(required = true) Map<String, String> requestMap);

    //Update plan
    @PostMapping (path="/update")
    public ResponseEntity<String> updatePlan(@RequestBody(required = true) Map<String, String> requestMap);

    //Delete plan
    @PostMapping(path="/delete")
    public ResponseEntity<String> deletePlan(@RequestBody(required = true) Map<String, String> requestMap);

    //Retrieve Plans
    @GetMapping(path="/allplans")
    public ResponseEntity<List<PlanWrapper>> getAllPlans();






}
