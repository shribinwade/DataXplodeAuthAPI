package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.FeatureModel.Features;
import com.dataxplode.auth.Models.PlanFeatureCommonModel.PlanFeatureTable;
import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.dataxplode.auth.dao.FeatureContentDAO.FeatureContentDAO;
import com.dataxplode.auth.dao.FeatureDAO.FeatureDAO;
import com.dataxplode.auth.dao.PincodeDAO.PincodeDAO;
import com.dataxplode.auth.dao.PlatformsDAO.PlatformDao;
import com.dataxplode.auth.dao.UserSubscriptionDAO.UserSubscriptionDao;
import com.dataxplode.auth.dao.CountryDAO.CountryDao;
import com.dataxplode.auth.dao.reviewsDAO.ReviewsDao;
import com.dataxplode.auth.service.KeywordSearchService;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class KeywordServiceImpl implements KeywordSearchService {

    private static final Logger log = LoggerFactory.getLogger(KeywordServiceImpl.class);


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
    ReviewsDao reviewsDao;



    @Override
    public ResponseEntity<String> addKeyword(Map<String, String> requestMap) {

        // Get User's Subscription details
        UserSubscription userSubDetail = userSubscriptionDao.findByUser_UserId(
                Long.parseLong(requestMap.get("userId"))
        );

        if (userSubDetail == null) {
            return Utils.getResponseEntity("User subscription details not found", HttpStatus.BAD_REQUEST);
        }

        Plan plan = userSubDetail.getPlan();

        boolean featureExists = false;

        // Check if the feature "Keyword Search" exists in the user's plan
        for (PlanFeatureTable planFeatureTable : plan.getPlanFeature()) {
            Features feature = planFeatureTable.getFeature();
            String featureName = feature.getFeatureName();
            log.info("Feature Name: {}", featureName);
            if ("Keyword Search".equals(featureName)) { // Use equals() for string comparison
                featureExists = true;
                break;
            }
        }

        if (featureExists) {
            // Add keyword logic here
            log.info("Keyword Search feature is available.");

            // Add Keyword Logic
            String SearchData = requestMap.get("searchData");
            if (SearchData == null || SearchData.isEmpty()) {
                return Utils.getResponseEntity("SearchData is Missing ",HttpStatus.BAD_REQUEST);
            }

            Features keywordSearch = featureDAO.findByFeature_name("Keyword Search");
            Country countryName = countryDao.getCountryByName(requestMap.get("countryName"));

            Optional<Pincode> existingPincode = pincodeDao.findByPincode(requestMap.get("pincode"));

            Pincode pincode;

            if (existingPincode.isPresent()) {
                // Pincode exists, use the existing one
                pincode = existingPincode.get();
            } else {
                // Pincode doesn't exist, create a new one and save it
                pincode = new Pincode();
                pincode.setPincode(requestMap.get("pincode"));
                pincode = pincodeDao.save(pincode);  // Save new pincode
            }

            Optional<Platform> existingplatform = platformDao.findByPlatformName(requestMap.get("platform"));
            Platform platform = null;
            if(existingplatform.isPresent()){
               platform = existingplatform.get();
            }else{
                Utils.getResponseEntity("Platform not found",HttpStatus.BAD_REQUEST);
            }

            Optional<Reviews> existingreviews = reviewsDao.findByreviewResult(requestMap.get("review"));
            Reviews reviews;
            if(existingreviews.isPresent()){
                reviews = existingreviews.get();
            }else{
                reviews = new Reviews();
                reviews.setReviewResult(requestMap.get("reviewResult"));
                reviews= reviewsDao.save(reviews);
            }


            // Create and save the keyword entry
            FeatureContentModel newKeyword = new FeatureContentModel();
            newKeyword.setFeature(keywordSearch);
            newKeyword.setSearchData(SearchData);
            newKeyword.setCountry(countryName);
            newKeyword.setPincode(pincode);
            newKeyword.setPlatform(platform);
            newKeyword.setCreatedAt(LocalDate.now());
            newKeyword.setUpdatedAt(LocalDate.now());
            newKeyword.setReview(reviews);
            featureContentDAO.save(newKeyword);






            return ResponseEntity.ok("Keyword added successfully.");
        } else {
            log.warn("Keyword Search feature is not available for this plan.");
            return Utils.getResponseEntity("Feature not available in your plan.", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<UserSubscription> getKeyword(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteKeyword(Map<String, String> requestMap) {
        return null;
    }
}
