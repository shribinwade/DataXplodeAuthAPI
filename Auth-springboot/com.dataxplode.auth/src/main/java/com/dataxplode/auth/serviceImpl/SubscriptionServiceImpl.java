
package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.SubscriptionStatus;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.Models.planModel.BillingCycle;
import com.dataxplode.auth.Models.planModel.Plan;

import com.dataxplode.auth.dao.PlanDAO.PlanDao;
import com.dataxplode.auth.dao.UserSubscriptionDAO.UserSubscriptionDao;
import com.dataxplode.auth.service.SubscriptionService;
import com.dataxplode.auth.service.UserService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.UserSearchDataWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private UserService userService;

    @Autowired
    UserSubscriptionDao userSubscriptionDao;

    @Autowired
    PlanDao planDao;

    private static final Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);

    @Override
    public UserSubscription createSubscription(User user) {
        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(12));
        subscription.setNextBillingDate(LocalDate.now().plusMonths(12));
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        Plan freePlan = planDao.getFreePlan();
        freePlan.setBillingCycle(BillingCycle.FREE);
        Plan p = new Plan();
        subscription.setPlan(freePlan);
        UserSubscription userSubscription = userSubscriptionDao.save(subscription);

        return userSubscription;
    }


    @Override
    public ResponseEntity<UserSubscription> getUsersSubscription(Map<String, String> requestMap) {
         UserSubscription subscription = new UserSubscription();
        try {
            UserSubscription userSubscription = userSubscriptionDao.findByUser_UserId(Long.parseLong(requestMap.get("userId")));
            if(!isValid(userSubscription)){
                          userSubscription.setPlan(planDao.getFreePlan());
                          userSubscription.setEndDate(LocalDate.now().plusMonths(1));
                          subscription.setStartDate(LocalDate.now());
            }
            UserSubscription saveUserSubscription = userSubscriptionDao.save(userSubscription);
            return new ResponseEntity<UserSubscription>(saveUserSubscription,HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error occurred while deleting plan", ex);
        }
        return new ResponseEntity<UserSubscription>(subscription,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<UserSubscription> upgradeSubscription(Map<String, String> requestMap) {

        try{
            UserSubscription userSubscription = userSubscriptionDao.findByUser_UserId(Long.parseLong(requestMap.get("userId")));
            Optional<Plan> optionalPlan = planDao.findById(Long.parseLong(requestMap.get("planId")));
            Plan newUserPlan = optionalPlan.get();
            userSubscription.setPlan(newUserPlan);
            userSubscription.setStartDate(LocalDate.now());
            if(newUserPlan.getBillingCycle() == BillingCycle.ANNUALLY){
                userSubscription.setEndDate(LocalDate.now().plusMonths(12));
                userSubscription.setNextBillingDate(LocalDate.now().plusMonths(12));
            }else if(newUserPlan.getBillingCycle() == BillingCycle.MONTHLY){
                userSubscription.setEndDate(LocalDate.now().plusMonths(1));
                userSubscription.setNextBillingDate(LocalDate.now().plusMonths(1));
            }
            else if(newUserPlan.getBillingCycle() == BillingCycle.QUARTERLY){
                userSubscription.setEndDate(LocalDate.now().plusMonths(3));
                userSubscription.setNextBillingDate(LocalDate.now().plusMonths(3));
            }
            else if(newUserPlan.getBillingCycle() == BillingCycle.HALF_YEARLY){
                userSubscription.setEndDate(LocalDate.now().plusMonths(6));
                userSubscription.setNextBillingDate(LocalDate.now().plusMonths(6));
            }
            else{
                userSubscription.setEndDate(LocalDate.now().plusDays(15));
                userSubscription.setNextBillingDate(LocalDate.now().plusDays(15));
            }
            userSubscriptionDao.save(userSubscription);
            return new ResponseEntity<>(userSubscription,HttpStatus.OK);
        }catch(Exception ex){
            log.error("Error occurred while updating plan",ex);
        }
        return null;
    }

    @Override
    public ResponseEntity<List<UserSearchDataWrapper>>  getUserSearchData(Integer UserID, String country, String platform) {
        try {
            List<UserSearchDataWrapper> searchData = userSubscriptionDao.findUserSearchDataQuery(Long.valueOf(UserID), country, platform);
            if (searchData.isEmpty()) {
                log.info("Search Data: {}", searchData);
                UserSearchDataWrapper defaultData = new UserSearchDataWrapper(
                        UserID.longValue(),
                        country,
                        platform,
                        0L, 0L, 0L, 0L, 0L, 0L
                );
                searchData.add(defaultData);
                return new ResponseEntity<>(searchData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(searchData, HttpStatus.OK);
            }
        } catch (Exception ex) {
            log.error("Error occurred while retrieving product data", ex);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean isValid(UserSubscription userSubscription) {
        Plan plan = userSubscription.getPlan();
        if (plan.getBillingCycle().equals(BillingCycle.FREE)){
            return true;
        }
        LocalDate endDate = userSubscription.getEndDate();
        LocalDate currentDate = LocalDate.now();

        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }

}


