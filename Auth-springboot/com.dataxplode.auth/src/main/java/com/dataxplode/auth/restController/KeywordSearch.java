package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/keywordSearch")
public interface KeywordSearch {

    @PostMapping(path = "/addkeywordSearch")
    ResponseEntity<String> addKeyword(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/getkeywordSearch")
    ResponseEntity<UserSubscription> getKeyword(@RequestBody(required = true) Map<String, String> requestMap);

    @DeleteMapping(path = "/deletekeywordSearch")
    ResponseEntity<UserSubscription> deleteKeyword(@RequestBody(required = true) Map<String, String> requestMap);
}
