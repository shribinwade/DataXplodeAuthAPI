package com.dataxplode.auth.restController;
import com.dataxplode.auth.wrapper.PlatformWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/platform")
public interface PlatformRest {

    //To-Do
    //Create Platform
    @PostMapping(path="/create")
     ResponseEntity<String> createPlatform(@RequestBody(required = true) Map<String, String> requestMap);

    //Delete Platform
    @PostMapping(path="/delete")
     ResponseEntity<String> deletePlatform(@RequestBody(required = true) Map<String, String> requestMap);

    //Retrieve Platforms
    @GetMapping(path="/allplatform")
     ResponseEntity<List<PlatformWrapper>> getAllPlatform();


}
