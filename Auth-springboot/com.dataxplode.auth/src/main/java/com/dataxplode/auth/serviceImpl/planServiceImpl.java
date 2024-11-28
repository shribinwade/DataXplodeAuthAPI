package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.JWT.JwtFilter;

import com.dataxplode.auth.Models.planModel.BillingCycle;
import com.dataxplode.auth.Models.planModel.Plan;

import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.dao.PlanDAO.PlanDao;
import com.dataxplode.auth.service.PlanService.PlanService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.PlanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class planServiceImpl implements PlanService {

    @Autowired
    PlanDao planDao;

    @Autowired
    JwtFilter jwtFilter;

    private static final Logger log = LoggerFactory.getLogger(planServiceImpl.class);
    @Override
    public ResponseEntity<String> createPlan(Map<String, String> requestMap) {
          log.info("Inside the createPlan method");
          try{
              if(jwtFilter.isAdmin()) {
                  if (validatePlanMap(requestMap)) {
                      Plan plan = planDao.findByName(requestMap.get("planName"));
                      if (Objects.isNull(plan)) {
                          planDao.save(getPlanFromMap(requestMap));
                          return Utils.getResponseEntity(Constants.PLANADDEDSUCCESSFULLY, HttpStatus.OK);
                      } else {
                          return Utils.getResponseEntity(Constants.PLANALREADYEXISTS, HttpStatus.BAD_REQUEST);
                      }
                  } else {
                      return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                  }
              }else{
                  return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
              }
          }catch(Exception ex){
              log.error("Error occurred while creating plan", ex);
          }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updatePlan(Map<String, String> requestMap) {
        log.info("Inside the updatePlan method");
        try{
            if(jwtFilter.isAdmin()) {
                Optional<Plan> optional = planDao.findById(Long.parseLong(requestMap.get("planId")));

                if (validatePlanMap(requestMap)) {
                    if (optional.isPresent()) {
                        Plan plan1 = optional.get();
                        plan1.setPlanname(requestMap.get("planName"));
                        plan1.setBillingCycle (BillingCycle.MONTHLY);
                        plan1.setDescription(requestMap.get("description"));
                        plan1.setPlanprice(Double.parseDouble( requestMap.get("price")));
                        plan1.setSearchLimit(Integer.parseInt(requestMap.get("searchLimit")));
                        planDao.save(plan1);
                        return Utils.getResponseEntity(Constants.PLANUPDATEDSUCCESSFUllY, HttpStatus.OK);
                    } else {
                        return Utils.getResponseEntity(Constants.PLANNOTEXISTS, HttpStatus.NOT_FOUND);
                    }
                } else {
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            }else{
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            log.error("Error occurred while updating plan", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> deletePlan(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                Optional<Plan> optional = planDao.findById(Long.parseLong(requestMap.get("planId")));
                if (optional.isPresent()){
                    planDao.deleteById(Long.parseLong(requestMap.get("id")));
                    return Utils.getResponseEntity("Plan Deleted Successfully", HttpStatus.OK);
                }else{
                    return Utils.getResponseEntity("Plan Id Not Found", HttpStatus.OK);
                }
            }else{
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            log.error("Error occurred while deleting plan", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<PlanWrapper>> getAllPlans() {
        log.info("Inside the getAllPlans method");
        List<PlanWrapper> list = new ArrayList<>();
        try{
            List<PlanWrapper> allPlans = planDao.getAllPlans();
            return new ResponseEntity<>(allPlans, HttpStatus.OK);
        }catch (Exception ex){
            log.error("Error occurred while getting plans", ex);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    private boolean validatePlanMap(Map<String, String> requestMap) {
        return requestMap.containsKey("planName") &&
                requestMap.containsKey("searchLimit") &&
                requestMap.containsKey("price") &&
                requestMap.containsKey("description") &&
                requestMap.containsKey("billingCycle");
    }

   private Plan getPlanFromMap(Map<String, String> requestMap){
        Plan newplan = new Plan();
             newplan.setPlanname(requestMap.get("planName"));
             if(requestMap.get("billingCycle").equals("monthly")){
                 newplan.setBillingCycle(BillingCycle.MONTHLY);
             }else if(requestMap.get("billingCycle").equals("free")){
                 newplan.setBillingCycle(BillingCycle.FREE);
             }

             newplan.setDescription(requestMap.get("description"));
             newplan.setPlanprice(Double.parseDouble( requestMap.get("price")));
             newplan.setSearchLimit(Integer.parseInt(requestMap.get("searchLimit")));
        return newplan;
    }

}