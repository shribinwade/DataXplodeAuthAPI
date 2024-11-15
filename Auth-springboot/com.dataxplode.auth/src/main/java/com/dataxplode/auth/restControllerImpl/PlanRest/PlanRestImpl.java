package com.dataxplode.auth.restControllerImpl.PlanRest;

import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.PlanRest;
import com.dataxplode.auth.service.PlanService.PlanService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.PlanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PlanRestImpl implements PlanRest {

    @Autowired
    PlanService planService;

    @Override
    public ResponseEntity<String> createPlan(Map<String, String> requestMap) {
        try{
            return  planService.createPlan(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updatePlan(Map<String, String> requestMap) {
        try{
            return  planService.updatePlan(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> deletePlan(Map<String, String> requestMap) {
        try{
            return  planService.deletePlan(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<PlanWrapper>> getAllPlans() {
        try{
            return  planService.getAllPlans();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<PlanWrapper>>(new ArrayList<>(),HttpStatus.OK);

    }

}
