package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.DTO.UserSearchHistoryDTO;
import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.UserSubscriptionRest;
import com.dataxplode.auth.service.SubscriptionService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.UserSearchDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @Override
    public ResponseEntity<List<UserSearchDataWrapper>> getUserSearchData(Integer UserID, String country, String platform) {
         return subscriptionService.getUserSearchData(UserID, country, platform);
    }

    @Override
    public ResponseEntity<List<UserSearchHistoryDTO>> getUserSearchData(Long userId, String featureName) {
        return subscriptionService.getUserKeywordSearchData(userId, featureName);
    }


}
