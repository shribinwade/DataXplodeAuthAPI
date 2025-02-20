package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.FeatureModel.Features;
import com.dataxplode.auth.Models.PlanFeatureCommonModel.PlanFeatureTable;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.dataxplode.auth.dao.CountryDAO.CountryDao;
import com.dataxplode.auth.dao.FeatureContentDAO.FeatureContentDAO;
import com.dataxplode.auth.dao.FeatureDAO.FeatureDAO;
import com.dataxplode.auth.dao.PincodeDAO.PincodeDAO;
import com.dataxplode.auth.dao.PlatformsDAO.PlatformDao;
import com.dataxplode.auth.dao.UserDao;
import com.dataxplode.auth.dao.UserSubscriptionDAO.UserSubscriptionDao;
import com.dataxplode.auth.service.CompetitveAnalysisService;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompetitveAnalysisServiceImpl implements CompetitveAnalysisService {


    @Autowired
    FeatureDAO featureDAO;

    @Autowired
    UserSubscriptionDao userSubscriptionDao;

    @Autowired
    CountryDao countryDao;

    @Autowired
    PincodeDAO pincodeDao;

    @Autowired
    PlatformDao platformDao;

    @Autowired
    FeatureContentDAO featureContentDAO;

    @Autowired
    UserDao userDao;



    private static final Logger log = LoggerFactory.getLogger(KeywordServiceImpl.class);


    @Override
    public ResponseEntity<String> addCompetitiveAnalysis(Map<String, String> requestMap) {

        try{

            if (validateRequest(requestMap)) {

                // Get User's Subscription details
                UserSubscription userSubDetail = userSubscriptionDao.findByUser_UserId(
                        Long.parseLong(requestMap.get("userId"))
                );

                if (userSubDetail == null) {
                    return Utils.getResponseEntity("User subscription details not found", HttpStatus.BAD_REQUEST);
                }

                Plan plan = userSubDetail.getPlan();
                boolean featureExists = false;

                // Check if the feature "Product Search" exists in the user's plan
                for (PlanFeatureTable planFeatureTable : plan.getPlanFeature()) {
                    Features feature = planFeatureTable.getFeature();
                    String featureName = feature.getFeatureName();
                    log.info("Feature Name: {}", featureName);
                    if ("Competitor Analysis Search".equals(featureName)) { // Use equals() for string comparison
                        featureExists = true;
                        break;
                    }
                }

                if(featureExists){

                    Optional<User> userId = userDao.findByUserId(Long.parseLong(requestMap.get("userId")));

                    User user = userId.get();

                    //Feature
                    Features CompetitorAnalysisFeature = featureDAO.findByFeature_name("Competitor Analysis Search");

                    //DistributorQuery
                    String competitiveSearchQuery= requestMap.get("competitorAnalysisQuery");
                    //Country
                    Country countryName = countryDao.getCountryByName(requestMap.get("countryName"));

                    //SearchData
                    String SearchData = requestMap.get("searchData");

                    //Platform
                    Optional<Platform> existingplatform = platformDao.findByPlatformName(requestMap.get("platform"));
                    Platform platform = null;
                    if(existingplatform.isPresent()){
                        platform = existingplatform.get();
                    }else{
                        Utils.getResponseEntity("Competitor Analysis Search not found",HttpStatus.BAD_REQUEST);
                    }

                    // Create and save the Competitor Analysis entry
                    FeatureContentModel newDistributors = new FeatureContentModel();
                    newDistributors.setFeature(CompetitorAnalysisFeature);
                    newDistributors.setSearchData(SearchData);
                    newDistributors.setCountry(countryName);
                    newDistributors.setPlatform(platform);
                    newDistributors.setCreatedAt(LocalDate.now());
                    newDistributors.setUpdatedAt(LocalDate.now());
                    newDistributors.setCompetitiorAnalysisQuery(competitiveSearchQuery);
                    newDistributors.setUser(user);

                    try{
                        featureContentDAO.save(newDistributors);
                    }catch(DataIntegrityViolationException e){
                        log.info(String.valueOf(e));
                        return Utils.getResponseEntity("Competitor Analysis  Data already exists", HttpStatus.CONFLICT);
                    }
                    return Utils.getResponseEntity("Competitor Analysis Data Added Successfully",HttpStatus.ACCEPTED);
                }else{
                    return Utils.getResponseEntity("Upgrade Plan for Competitor Analysis Search", HttpStatus.BAD_REQUEST);
                }

            }else {
                return Utils.getResponseEntity("Invalid request data", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            log.error("Failed to add Product", ex);
        }
        return Utils.getResponseEntity("Failed to add Competitor Analysis Data", HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<UserSubscription> getCompetitiveAnalysis(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteCompetitiveAnalysis(Map<String, String> requestMap) {
        return null;
    }

    private boolean validateRequest(Map<String, String> requestMap) {
        // Define the required keys
        List<String> requiredKeys = Arrays.asList(
                "userId",        // ID of the user
                "countryName",     // ID of the country
                "platform",    // ID of the platform// ID of the review
                "competitorAnalysisQuery",  // Search query text
                "searchData"  // JSON search results
        );
        // Check if all required keys exist and are non-empty
        for (String key : requiredKeys) {
            if (!requestMap.containsKey(key) ) {
                return false; // Return false if a key is missing or its value is empty
            }
        }
        return true; // Validation passed
    }

    @Override
    public ResponseEntity<String> getCompetitorAnalysis(Integer UserID, String country,String competitor){
        try {
            Optional<String> searchData = userSubscriptionDao
                    .findSearchDataByUserAndCountryAndCompetitorAnalysisQuery
                    (Long.valueOf(UserID) , country, competitor);

            if (searchData.isPresent()) {
                log.info("Search Data: {}", searchData.get());
                return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
            } else {
                return Utils.getResponseEntity("No data found for " + competitor, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Error occurred while retrieving Competitor Analysis data", ex);
        }
        return Utils.getResponseEntity("An error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
