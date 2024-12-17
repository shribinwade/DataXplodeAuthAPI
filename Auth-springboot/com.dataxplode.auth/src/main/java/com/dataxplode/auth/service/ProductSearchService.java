package com.dataxplode.auth.service;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ProductSearchService {


    ResponseEntity<String> addProduct( Map<String, String> requestMap);

    ResponseEntity<UserSubscription> getProduct( Map<String, String> requestMap);

    ResponseEntity<UserSubscription> deleteProduct( Map<String, String> requestMap);

    ResponseEntity<String> getProductData( Integer UserID, String country, String product);

    ResponseEntity<String> getProductReview(Integer UserID,String country, String product);


}
