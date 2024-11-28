package com.dataxplode.auth.serviceImpl;
import com.dataxplode.auth.JWT.JwtFilter;

import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.dao.PlatformsDAO.PlatformDao;
import com.dataxplode.auth.service.PlatformService.PlatformService;
import com.dataxplode.auth.utils.Utils;

import com.dataxplode.auth.wrapper.PlatformWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    PlatformDao platformDao;

    @Autowired
    JwtFilter jwtFilter;

    private static final Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);

    @Override
    public ResponseEntity<String> createPlatform(Map<String, String> requestMap) {
        log.info("Inside the createPlatform method");
        try{
            if(jwtFilter.isAdmin()) {
                if (validatePlatformMap(requestMap)) {
                    Optional<Platform> plan = platformDao.findByPlatformName(requestMap.get("platformName"));
                    if (plan.isEmpty()) {
                        platformDao.save(getPlatformFromMap(requestMap));
                        return Utils.getResponseEntity(Constants.PLATFORMADDEDSUCCESSFULLY, HttpStatus.OK);
                    } else {
                        return Utils.getResponseEntity(Constants.PLATFORMALREADYEXISTS, HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            }else{
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            log.error("Error occurred while creating platform", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deletePlatform(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                Optional<Platform> optional = platformDao.findById(Long.parseLong(requestMap.get("platformId")));
                if (optional.isPresent()){
                    platformDao.deleteById(Long.parseLong(requestMap.get("platformId")));
                    return Utils.getResponseEntity("Platform Deleted Successfully", HttpStatus.OK);
                }else{
                    return Utils.getResponseEntity("Platform Id Not Found", HttpStatus.OK);
                }
            }else{
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            log.error("Error occurred while deleting platform", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<PlatformWrapper>> getAllPlatform() {
        log.info("Inside the getAllPlatform method");
        List<PlatformWrapper> list = new ArrayList<>();
        try{
            List<PlatformWrapper> allPlatforms = platformDao.getAllPlatforms();
            return new ResponseEntity<>(allPlatforms,HttpStatus.OK);
        }catch (Exception ex){
            log.error("Error occurred while getting all platforms", ex);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    boolean validatePlatformMap(Map<String, String> requestMap) {
        return requestMap.containsKey("platformName");
    }

    private Platform getPlatformFromMap(Map<String, String> requestMap){
        Platform newplatform = new Platform();
        newplatform.setPlatformName(requestMap.get("platformName"));
        return newplatform;
    }
}


