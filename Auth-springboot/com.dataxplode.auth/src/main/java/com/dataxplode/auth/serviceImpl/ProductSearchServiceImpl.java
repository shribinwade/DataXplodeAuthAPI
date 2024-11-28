package com.dataxplode.auth.serviceImpl;

import org.springframework.stereotype.Service;

@Service
public class ProductSearchServiceImpl  {

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
//    private static final Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);
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
//    @Override
//    public ResponseEntity<UserSubscription> getProduct(Map<String, String> requestMap) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<UserSubscription> deleteProduct(Map<String, String> requestMap) {
//        return null;
//    }
//
//    private boolean validateRequest(Map<String, String> requestMap) {
//        // Define the required keys
//        List<String> requiredKeys = Arrays.asList(
//                "userId",        // ID of the user
//                "serviceId",     // ID of the service
//                "countryId",     // ID of the country
//                "pincode",       // Value of the pincode
//                "platformId",    // ID of the platform
//                "reviewResult",      // ID of the review
//                "searchQuery",   // Search query text
//                "searchResults"  // JSON search results
//        );
//        // Check if all required keys exist and are non-empty
//        for (String key : requiredKeys) {
//            if (!requestMap.containsKey(key) ) {
//                return false; // Return false if a key is missing or its value is empty
//            }
//        }
//        return true; // Validation passed
//    }
}
