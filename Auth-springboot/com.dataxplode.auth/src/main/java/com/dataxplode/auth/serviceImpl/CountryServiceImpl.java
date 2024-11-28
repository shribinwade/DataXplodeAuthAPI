package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.JWT.JwtFilter;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.dao.CountryDAO.CountryDao;
import com.dataxplode.auth.service.CountryService.CountryService;
import com.dataxplode.auth.utils.Utils;
import com.dataxplode.auth.wrapper.CountryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;
    @Autowired
    JwtFilter jwtFilter;

    private static final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);


    @Override
    public ResponseEntity<List<CountryWrapper>> getAllCountry() {
        try{
            return new ResponseEntity<>(countryDao.getALlCountries(),HttpStatus.OK);
        }catch(Exception ex){
            log.error("Error occurred while getting Countries", ex);
        }
        return new ResponseEntity <>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addCountry(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                  countryDao.save(getCountryfromMap(requestMap));
                return Utils.getResponseEntity(Constants.ADDED,HttpStatus.OK);
            }else{
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            log.error("Error occurred while Adding Country", ex);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST);
    }

  private Country getCountryfromMap(Map<String, String> requestMap){
        Country country = new Country();
        country.setCountryName(requestMap.get("countryName"));
        return country;
    }
  }
