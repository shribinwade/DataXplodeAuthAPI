package com.dataxplode.auth.restControllerImpl.CountryRest;

import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.CountryRest;
import com.dataxplode.auth.service.CountryService.CountryService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.CountryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CountryRestImpl implements CountryRest {

    @Autowired
    CountryService countryService;


    @Override
    public ResponseEntity<List<CountryWrapper>> getAllCountry() {
        try{
            return countryService.getAllCountry();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<List<CountryWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addCountry( Map<String, String> requestMap) {

        try{
            return countryService.addCountry( requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCountry(Map<String, Integer> requestMap) {
        return null;
    }
}
