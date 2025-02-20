package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.FeatureModel.Features;
import com.dataxplode.auth.Models.PlanFeatureCommonModel.PlanFeatureTable;
import com.dataxplode.auth.Models.ReviewsModel.Reviews;
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
import com.dataxplode.auth.dao.reviewsDAO.ReviewsDao;
import com.dataxplode.auth.service.ProductSearchService;
import com.dataxplode.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dataxplode.auth.Models.pincodeModel.Pincode;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {


//    @Autowired
//    ProductSearchDao productSearchDao;
//
//    @Autowired
//    CountryDao countryDao;
//
//    @Autowired
//    PlatformDao platformDao;
//
//    @Autowired
//    ServicePlatformDao servicePlatformDao;
//
//    @Autowired
//    UserDao userDao;
//
//

//
//
//    @Override
//    @Transactional
//    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
//        try {
//            if (validateRequest(requestMap)) {
//
//                // Create Pincode
//                Pincode pincode = new Pincode();
//                pincode.setPincode(requestMap.get("pincode"));
//
//
//                // Retrieve and validate Country
//                Optional<Country> countryId = countryDao.findById(Integer.parseInt(requestMap.get("countryId")));
//                if (!countryId.isPresent()) {
//                    return Utils.getResponseEntity("Country not found", HttpStatus.NOT_FOUND);
//                }
//                Country country = countryId.get();
//
//
//                // Retrieve and validate Platform
//                Optional<Platform> platformId = platformDao.findById(Long.parseLong(requestMap.get("platformId")));
//                if (!platformId.isPresent()) {
//                    return Utils.getResponseEntity("Platform not found", HttpStatus.NOT_FOUND);
//                }
//                Platform platform = platformId.get();
//
//                // Create Review
//                ObjectMapper objectMapper = new ObjectMapper();
//                String jsonReviewString = objectMapper.writeValueAsString(requestMap.get("reviewResult"));
//                log.info("Json String+++++++: "+jsonReviewString);
//                Reviews reviews = new Reviews();
//                reviews.setReviewResult(jsonReviewString);
//
//
//                // Retrieve and validate ServicePlatform
//                Optional<ServicePlatform> serviceId = servicePlatformDao.findById(Long.parseLong(requestMap.get("serviceId")));
//                if (!serviceId.isPresent()) {
//                    return Utils.getResponseEntity("Service Platform not found", HttpStatus.NOT_FOUND);
//                }
//                ServicePlatform servicePlatform = serviceId.get();
//
//                // Retrieve and validate User
//                Optional<User> optionalUser = userDao.findByUserId(Long.parseLong(requestMap.get("userId")));
//                if (!optionalUser.isPresent()) {
//                    return Utils.getResponseEntity("User not found", HttpStatus.NOT_FOUND);
//                }
//                User user = optionalUser.get();
//
//                // Create ProductSearchData
//                ProductSearchData productSearchData = new ProductSearchData();
//
//                productSearchData.setSearchQuery(requestMap.get("searchQuery"));
//                ObjectMapper objectMapper1 = new ObjectMapper();
//                String jsonResultString = objectMapper1.writeValueAsString(requestMap.get("searchResults"));
//
//                productSearchData.setCreatedAt(LocalDateTime.now());
//                productSearchData.setSearchResults(jsonResultString);
//                productSearchData.setCountry(country);
//                productSearchData.setPlatform(platform);
//                productSearchData.setPincode(pincode);
//                productSearchData.setReview(reviews);
//                productSearchData.setService(servicePlatform);
//                productSearchData.setUser(user);
//
//                // Save ProductSearchData to the database
//                productSearchDao.save(productSearchData);
//
//                return Utils.getResponseEntity("Product Search Data added successfully", HttpStatus.OK);
//
//            } else {
//                return Utils.getResponseEntity("Invalid request data", HttpStatus.BAD_REQUEST);
//            }
//        } catch (NoSuchElementException e) {
//            log.error("Error: Record not found", e);
//            return Utils.getResponseEntity("Record not found", HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            log.error("Failed to add Product", ex);
//            return Utils.getResponseEntity("Failed to add Product Search Data", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

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

    @Autowired
    UserDao userDao;

    private static final Logger log = LoggerFactory.getLogger(ProductSearchServiceImpl.class);


    @Override
    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {

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
                    if ("Product Search".equals(featureName)) { // Use equals() for string comparison
                        featureExists = true;
                        break;
                    }
                }

                if(featureExists){
                    //user
                    Optional<User> userId = userDao.findByUserId(Long.parseLong(requestMap.get("userId")));

                    User user = userId.get();
                    //Feature
                    Features productSearch = featureDAO.findByFeature_name("Product Search");

                    //ProductQuery
                    String ProductQuery= requestMap.get("productSearchQuery");
                    //Country
                    Country countryName = countryDao.getCountryByName(requestMap.get("countryName"));

                    //SearchData
                    String SearchData = requestMap.get("searchData");

                    //Pincode
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

                    //Platform
                    Optional<Platform> existingplatform = platformDao.findByPlatformName(requestMap.get("platform"));
                    Platform platform = null;
                    if(existingplatform.isPresent()){
                        platform = existingplatform.get();
                    }else{
                        Utils.getResponseEntity("Platform not found",HttpStatus.BAD_REQUEST);
                    }

                    //Reviews
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
                    FeatureContentModel newProduct = new FeatureContentModel();
                    newProduct.setFeature(productSearch);
                    newProduct.setSearchData(SearchData);
                    newProduct.setCountry(countryName);
                    newProduct.setPlatform(platform);
                    newProduct.setPincode(pincode);
                    newProduct.setCreatedAt(LocalDate.now());
                    newProduct.setUpdatedAt(LocalDate.now());
                    newProduct.setProductQuery(ProductQuery);
                    newProduct.setReview(reviews);
                    newProduct.setUser((user));

                    try{
                        featureContentDAO.save(newProduct);
                    }catch(DataIntegrityViolationException e){
                        log.info(String.valueOf(e));
                        return Utils.getResponseEntity("Product Data already exists", HttpStatus.CONFLICT);
                    }
                    return Utils.getResponseEntity("Product Data Added Successfully",HttpStatus.ACCEPTED);
                }

            }else {
                return Utils.getResponseEntity("Invalid request data", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            log.error("Failed to add Product", ex);
        }
        return Utils.getResponseEntity("Failed to add Product Search Data", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserSubscription> getProduct(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<UserSubscription> deleteProduct(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> getProductData(Integer UserID, String country, String product) {
        try {
            Optional<String> searchData = userSubscriptionDao.findSearchDataByUserAndCountryAndProductQuery(
                    Long.valueOf(UserID), country, product);

            if (searchData.isPresent()) {
                log.info("Search Data: {}", searchData.get());
                return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
            } else {
                return Utils.getResponseEntity("No data found for " + product, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Error occurred while retrieving product data", ex);
        }
        return Utils.getResponseEntity("An error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> getProductReview(Integer UserID, String country, String product) {
        try {
            Optional<String> searchData = userSubscriptionDao.findProductReviewByProductQuery(
                    Long.valueOf(UserID),country, product);

            if (searchData.isPresent()) {
                log.info("Search Data: {}", searchData.get());
                return new ResponseEntity<>(searchData.get(), HttpStatus.OK);
            } else {
                return Utils.getResponseEntity("No data found for " + product, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Error occurred while retrieving product data", ex);
        }
        return Utils.getResponseEntity("An error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateRequest(Map<String, String> requestMap) {
        // Define the required keys
        List<String> requiredKeys = Arrays.asList(
                "userId",        // ID of the user
                "countryName",     // ID of the country
                "pincode",       // Value of the pin code
                "platform",    // ID of the platform
                "reviewResult",      // ID of the review
                "productSearchQuery",  // Search query text
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
}
