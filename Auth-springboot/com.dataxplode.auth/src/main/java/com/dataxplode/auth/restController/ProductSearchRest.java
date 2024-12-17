package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RequestMapping(path = "/productSearch")
public interface ProductSearchRest {

        @PostMapping(path = "/addProductSearch")
        ResponseEntity<String> addProduct(@RequestBody(required = true) Map<String, String> requestMap);

        @PostMapping(path = "/getProductSearch")
        ResponseEntity<UserSubscription> getProduct(@RequestBody(required = true) Map<String, String> requestMap);

        @DeleteMapping(path = "/deleteProduct")
        ResponseEntity<UserSubscription> deleteProduct(@RequestBody(required = true) Map<String, String> requestMap);

        @GetMapping("/getProductData")
        ResponseEntity<String> searchProduct(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String product);

        @GetMapping("/getProductReview")
        ResponseEntity<String> searchProductReview(@RequestParam Integer UserID,  @RequestParam String country ,@RequestParam String product);


}
