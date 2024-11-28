package com.dataxplode.auth.restControllerImpl;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.UserRest;
import com.dataxplode.auth.service.UserService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> test() {
        return Utils.getResponseEntity("Welcome to spring boot api ",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserDetails(Map<String, String> requestMap) {
       return userService.getUserDetails(requestMap);
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

     try{
         return  userService.signUp(requestMap);
     }catch (Exception ex){
         ex.printStackTrace();
     }
     return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> logIn(Map<String, String> requestMap){
        try{
            return userService.logIn(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
      return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        try{
             return userService.getAllUser();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<List<User>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
              return userService.update(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try{
            return userService.checkToken();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            return userService.changePassword(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
       try{
           return userService.forgotPassword(requestMap);
       }catch(Exception ex){
           ex.printStackTrace();
       }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> verifyAccount(String token) {
         try{
             return userService.verifyAccount(token);
         }catch (Exception ex){
             ex.printStackTrace();
         }
         return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> resetPassword(Map<String, String> requestMap) {
        try{
            return userService.resetPassword(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
