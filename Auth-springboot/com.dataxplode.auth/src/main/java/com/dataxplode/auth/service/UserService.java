package com.dataxplode.auth.service;

import com.dataxplode.auth.DTO.userDTO;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.wrapper.UserWrapper;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> logIn(Map<String, String> requestMap);

    ResponseEntity<List<User>> getAllUser();

    ResponseEntity<String> update(Map<String,String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String,String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String,String> requestMap);

    ResponseEntity<String> verifyAccount(String token);

    ResponseEntity<String> resetPassword(Map<String,String> requestMap);

    ResponseEntity<userDTO> getUserDetails(Map<String, String> requestMap);
}
