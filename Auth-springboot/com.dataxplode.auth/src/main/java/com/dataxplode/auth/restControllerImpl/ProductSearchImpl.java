package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.ProductSearchRest;
import com.dataxplode.auth.service.ProductSearchService;
import com.dataxplode.auth.serviceImpl.PlatformServiceImpl;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductSearchImpl implements ProductSearchRest {

    @Autowired
    ProductSearchService productSearchService;

    //Initializing Logger
    private static final Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);

    @Override
    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
       //return productSearchService.addProduct(requestMap);
        try{
            return productSearchService.addProduct( requestMap);
        }catch (Exception ex){
            log.info("Unable to add Product: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<UserSubscription> getProduct(Map<String, String> requestMap) {
//
//        try{
//            return productSearchService.addProduct( requestMap);
//        }catch (Exception ex){
//            log.info("Unable to add Product: ",ex);
//        }
//        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
      return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteProduct(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> searchProduct(Integer UserID, String country, String product) {
        try{
            return productSearchService.getProductData(UserID, country, product );
        }catch (Exception ex){
            log.info("Unable to add Distributor: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> searchProductReview(Integer UserID, String country, String product) {
        try{
            return productSearchService.getProductReview(UserID, country, product);
        }catch (Exception ex){
            log.info("Unable to add Distributor: ",ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
