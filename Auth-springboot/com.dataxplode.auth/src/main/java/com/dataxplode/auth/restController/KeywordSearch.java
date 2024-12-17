package com.dataxplode.auth.restController;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/keywordSearch")
public interface KeywordSearch {

    @PostMapping(path = "/addkeywordSearch")
    ResponseEntity<String> addKeyword(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping("/getKeywordData")
    ResponseEntity<String> search(@RequestParam Integer UserID, @RequestParam String country, @RequestParam String keyword );

    @PostMapping(path = "/getkeywordSearch")
    ResponseEntity<UserSubscription> getKeyword(@RequestBody(required = true) Map<String, String> requestMap);

    @DeleteMapping(path = "/deletekeywordSearch")
    ResponseEntity<UserSubscription> deleteKeyword(@RequestBody(required = true) Map<String, String> requestMap);
}
