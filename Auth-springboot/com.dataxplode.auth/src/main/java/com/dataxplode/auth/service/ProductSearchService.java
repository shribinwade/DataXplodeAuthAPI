package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ProductSearchService {


    ResponseEntity<String> addProduct( Map<String, String> requestMap);

    ResponseEntity<UserSubscription> getProduct( Map<String, String> requestMap);

    ResponseEntity<UserSubscription> deleteProduct( Map<String, String> requestMap);


}