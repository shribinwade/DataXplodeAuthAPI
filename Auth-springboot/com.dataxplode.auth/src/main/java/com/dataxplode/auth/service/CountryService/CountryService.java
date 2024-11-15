package com.dataxplode.auth.service.CountryService;

import com.dataxplode.auth.wrapper.CountryWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CountryService {

    ResponseEntity<List<CountryWrapper>> getAllCountry();

    ResponseEntity<String> addCountry(Map<String, String> requestMap);
}
