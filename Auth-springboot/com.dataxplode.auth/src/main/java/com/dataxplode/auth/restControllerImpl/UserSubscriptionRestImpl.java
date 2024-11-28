package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.UserSubscriptionRest;
import com.dataxplode.auth.service.SubscriptionService;
import com.dataxplode.auth.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserSubscriptionRestImpl implements UserSubscriptionRest {

    @Autowired
    SubscriptionService subscriptionService;


    @Override
    public ResponseEntity<String> createSubscription(User user) {
        try{
             subscriptionService.createSubscription(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> getUsersSubscription(Map<String, String> requestMap) {
           return subscriptionService.getUsersSubscription(requestMap);
    }

    @Override
    public ResponseEntity<UserSubscription> upgradeSubscription(Map<String, String> requestMap) {
        return subscriptionService.upgradeSubscription(requestMap);
    }


}
