package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@RequestMapping(path = "/productSearch")
public interface ProductSearchRest {

        @PostMapping(path = "/addProductSearch")
        ResponseEntity<String> addProduct(@RequestBody(required = true) Map<String, String> requestMap);

        @PostMapping(path = "/getProductSearch")
        ResponseEntity<UserSubscription> getProduct(@RequestBody(required = true) Map<String, String> requestMap);

        @DeleteMapping(path = "/deleteProduct")
        ResponseEntity<UserSubscription> deleteProduct(@RequestBody(required = true) Map<String, String> requestMap);

}
