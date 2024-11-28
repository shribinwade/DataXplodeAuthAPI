package com.dataxplode.auth.restControllerImpl.PlatformRest;


import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.restController.PlatformRest;
import com.dataxplode.auth.service.PlatformService.PlatformService;

import com.dataxplode.auth.utils.Utils;

import com.dataxplode.auth.wrapper.PlatformWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PlatformRestImpl implements PlatformRest {

    @Autowired
    PlatformService platformService;

    private static final Logger log = LoggerFactory.getLogger(PlatformRestImpl.class);

    @Override
    public ResponseEntity<String> createPlatform(Map<String, String> requestMap) {
        try{
            return  platformService.createPlatform(requestMap);
        }catch (Exception ex){
            log.error("Error occurred while creating platform", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> deletePlatform(Map<String, String> requestMap) {
        try{
            return  platformService.deletePlatform(requestMap);
        }catch (Exception ex){
            log.error("Error occurred while deleting platform", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<PlatformWrapper>> getAllPlatform() {
        try {
            return platformService.getAllPlatform();
        } catch (Exception ex) {
            log.error("Error occurred while getting platforms", ex);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
