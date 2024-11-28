package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.restController.ProductSearchRest;
import com.dataxplode.auth.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductSearchImpl implements ProductSearchRest {

//    @Autowired
//    ProductSearchService productSearchService;

    @Override
    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
//        return productSearchService.addProduct(requestMap);
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> getProduct(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteProduct(Map<String, String> requestMap) {
        return null;
    }
}
