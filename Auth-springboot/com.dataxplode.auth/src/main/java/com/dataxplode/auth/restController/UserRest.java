package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

//CONTEXT API URL
@RequestMapping(path = "/user")
public interface UserRest {

    //TEST ENDPOINT
    @GetMapping("/test")
    public ResponseEntity<String> test();

    @PostMapping("/getUserDetails")
    public ResponseEntity<User> getUserDetails(@RequestBody(required = true) Map<String,String> requestMap);

    //SIGNUP ENDPOINT
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "login")
    public ResponseEntity<String> logIn(@RequestBody(required = true) Map<String,String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<User>> getAllUser();

    @PostMapping(path = "/update")
    public ResponseEntity<String> update (@RequestBody(required = true) Map<String,String> requestMap);

    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap);

    @PostMapping(path= "/forgotPassword")
    ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap);

    // New endpoint for account activation
    @GetMapping(path = "/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token);

    @PostMapping(path = "/resetPassword")
    ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestMap);



}
