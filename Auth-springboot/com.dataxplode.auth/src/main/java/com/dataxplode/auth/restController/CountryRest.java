package com.dataxplode.auth.restController;

import com.dataxplode.auth.wrapper.CountryWrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/country")
public interface CountryRest {


    @GetMapping(path = "/get")
     ResponseEntity<List<CountryWrapper>> getAllCountry();

    @PostMapping(path="/add")
    ResponseEntity<String> addCountry(@RequestBody(required = true) Map<String, String> requestMap);

    @DeleteMapping(path="/delete")
    ResponseEntity<String> deleteCountry(@RequestBody(required = true) Map<String, Integer> requestMap);
}
