package com.dataxplode.auth.serviceImpl;

import com.dataxplode.auth.DTO.PlanDTO;
import com.dataxplode.auth.DTO.roleDTO;
import com.dataxplode.auth.DTO.userDTO;
import com.dataxplode.auth.DTO.userSubscriptionDTO;
import com.dataxplode.auth.Models.RoleModel.Role;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.JWT.CustomerUserDetailsService;
import com.dataxplode.auth.JWT.JwtFilter;
import com.dataxplode.auth.JWT.JwtUtil;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.constants.Constants;
import com.dataxplode.auth.dao.UserDao;
import com.dataxplode.auth.service.SubscriptionService;
import com.dataxplode.auth.service.UserService;
import com.dataxplode.auth.utils.EmailUtils;
import com.dataxplode.auth.utils.Utils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject the PasswordEncoder

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Value("${frontend.url}")
    private String frontendUrl;


    @Autowired
    private SubscriptionService subscriptionService;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup: {}", requestMap);
        try {
            // Extract email from requestMap
            String email = requestMap.get("email");


            if (validateSignUpMap(requestMap)) {
                //checking email in database if exists
                User user = userDao.findByEmailId(requestMap.get("email"));

                if (Objects.isNull(user)) {

                   User userContactNumber =  userDao.findByContactNumber(requestMap.get("contactNumber"));

                    if(Objects.isNull((userContactNumber))) {

                    //create getUserFromMap for adding remaining data

                    // Generate token
                    String token = UUID.randomUUID().toString();


                        User savedUser = userDao.save(getUserFromMap(requestMap, token));// Save user with hashed password

                        UserSubscription subscription = subscriptionService.createSubscription(savedUser);

                        savedUser.setSubscriptions(subscription);
                        userDao.save(savedUser);


                        // Send verification email
                    String verificationUrl = "http://127.0.0.1:8081/user/verify?token=" + token;
                    emailUtils.sendVerificationEmail(email, verificationUrl);


                    return Utils.getResponseEntity("User registered successfully! Please check your email for verification.", HttpStatus.OK);

                    } else {
                        return Utils.getResponseEntity("Phone no Already Exists", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return Utils.getResponseEntity("Email Already Exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.error("Exception during signup", ex);  // Log the error for debugging
        }

        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password") &&
                requestMap.containsKey("contactNumber");
    }

    private User getUserFromMap(Map<String, String> requestMap,String verification_token) {
        User user = new User();
        Role role  = new Role();
        role.setRoleId(2L);
        user.setUsername(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(requestMap.get("password")));
        user.setUserStatus("true");
        user.setRole(role);
        user.setEnabled(false); // Not enabled yet
        user.setVerificationToken(verification_token);
        return user;
    }

    @Override
    public ResponseEntity<String> logIn(Map<String, String> requestMap) {
        log.info("Inside the login Service", requestMap);

        try {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestMap.get("email"),
                                                        requestMap.get("password"))
        );
        if (auth.isAuthenticated()){
            if(customerUserDetailsService.getUserDetails().getUserStatus().equalsIgnoreCase("true")){
                log.info("UserDetails"+ customerUserDetailsService.getUserDetails().getEmail()+
                        customerUserDetailsService.getUserDetails().getRole()+
                        customerUserDetailsService.getUserDetails().getUsername()+
                        customerUserDetailsService.getUserDetails().getContactNumber()+
                        customerUserDetailsService.getUserDetails().getUserId()+
                        customerUserDetailsService.getUserDetails().getUserStatus()
                );
                return new ResponseEntity<String>("{\"token\":\""+
                        jwtUtil.generateToken(
                                customerUserDetailsService.getUserDetails().getEmail(),
                                customerUserDetailsService.getUserDetails().getRole(),
                                customerUserDetailsService.getUserDetails().getUsername(),
                                customerUserDetailsService.getUserDetails().getContactNumber(),
                                customerUserDetailsService.getUserDetails().getUserId(),
                                customerUserDetailsService.getUserDetails().getUserStatus()
                        )+ "\"}",HttpStatus.OK);

            }
            else{
                return new ResponseEntity<String>("{\"message\":\""+"Wait for Admin approval."+"\"}",HttpStatus.BAD_REQUEST);
            }
        }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        try{
          if(jwtFilter.isAdmin()) {
             return new ResponseEntity<>(userDao.getAllUser(),HttpStatus.OK);
          }else{
              return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
          }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity <>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            if(requestMap.isEmpty()){
                return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }else {
                if (jwtFilter.isAdmin()) {
                    Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                        sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
                        return Utils.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);

                    } else {
                        return Utils.getResponseEntity("User id doesn't not exist", HttpStatus.OK);
                    }
                } else {
                    return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
                }
            }
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }



    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
           allAdmin.remove(jwtFilter.getCurrentUser());
           if(status != null && status.equalsIgnoreCase("true")){
             emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approved", "USER:- "+ user + "\n is approved by \n ADMIN:-" +jwtFilter.getCurrentUser(), allAdmin);
           }else{
               emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disabled", "USER:- "+ user + "\n is Disabled by \n ADMIN:-" +jwtFilter.getCurrentUser(), allAdmin);

           }
    }

    @Override
    public ResponseEntity<String> checkToken() {
       return Utils.getResponseEntity("true",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
           User userObj =userDao.findByEmail(jwtFilter.getCurrentUser());
           if(BCrypt.checkpw(requestMap.get("oldPassword"),userObj.getPassword())){
               // Hash the new password before storing it
//                   String hashedNewPassword = BCrypt.hashpw(requestMap.get("newPassword"), BCrypt.gensalt());
                     String hashedNewPassword = BCrypt.hashpw(requestMap.get("newPassword"),BCrypt.gensalt());
                   userObj.setPassword(hashedNewPassword);
                   userDao.save(userObj);
                   return Utils.getResponseEntity("Password updated Successfully",HttpStatus.OK);
            }else{
               return Utils.getResponseEntity("Incorrect Old Password",HttpStatus.BAD_REQUEST);
           }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
       try{
           User user = userDao.findByEmail(requestMap.get("email"));
           if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())){
               // Generate a reset token using JWTUtil
               String resetToken = jwtUtil.generateResetToken(user.getEmail());

               // Construct the password reset link
               String resetLink = frontendUrl+"auth/reset-password?token=" + resetToken;

               // Send the reset link to the user's email
               emailUtils.forgotMail(user.getEmail(), "Reset Your Password",
                       "Click the link below to reset your password:\n" + resetLink);

               return Utils.getResponseEntity("Check your email for the password reset link.", HttpStatus.OK);
           }
           return Utils.getResponseEntity("Invalid user data.", HttpStatus.BAD_REQUEST);

       }catch(Exception ex){
           ex.printStackTrace();
       }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> verifyAccount(String token) {

        try{
            // Find user by token
            User user = userDao.findByVerificationToken(token);

            if (user == null) {
                return ResponseEntity.badRequest().body("Invalid token.");
            }

            // Activate the user account
            user.setEnabled(true);
            user.setVerificationToken(null); // Invalidate the token
            userDao.save(user);

            return ResponseEntity.ok("Account verified successfully! Proceed with login");

        }catch (Exception ex){
           ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> resetPassword(Map<String, String> requestMap) {

        try{
            String token = requestMap.get("token");
            String newPassword = requestMap.get("newPassword");

            // Validate the token
            if (!jwtUtil.validateResetToken(token)) {
                //return ResponseEntity.badRequest().body("Invalid or expired token.");
                return Utils.getResponseEntity("Invalid or expired token.", HttpStatus.BAD_REQUEST);
            }
            // Extract email from token claims
            String email = jwtUtil.getEmailFromToken(token);

            // Find user by email
            User user = userDao.findByEmail(email);
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            
            // Update the user's password
            user.setPassword(passwordEncoder.encode(newPassword));
            userDao.save(user);

            return Utils.getResponseEntity("Password has been changed successfully!", HttpStatus.OK);
        }catch (Exception ex){
           ex.printStackTrace();
        }

        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<userDTO> getUserDetails(Map<String, String> requestMap) {
        userDTO user = new userDTO();
        try{
            log.info(requestMap.toString());
            Optional<User> userDetails = userDao.findByUserId(Long.parseLong(requestMap.get("userId")));
            if(userDetails.isPresent()){
                User userDetail = userDetails.get();
                Role role = userDetail.getRole();
                UserSubscription subscriptions = userDetail.getSubscriptions();
                Plan plan = subscriptions.getPlan();

                roleDTO roleDTO = new roleDTO();
                userSubscriptionDTO userSubscriptionDTO = new userSubscriptionDTO();
                PlanDTO planDTO = new PlanDTO();

                BeanUtils.copyProperties(role,roleDTO);
                BeanUtils.copyProperties(userDetail, user);
                BeanUtils.copyProperties(subscriptions,userSubscriptionDTO);
                BeanUtils.copyProperties(plan,planDTO);

                userSubscriptionDTO.setPlan(planDTO);
                user.setRole(roleDTO);
                user.setSubscription(userSubscriptionDTO);
                return new ResponseEntity <>(user,HttpStatus.OK);
            }else{
                return new ResponseEntity <>(user,HttpStatus.NOT_FOUND);

            }

        }catch (Exception ex){
            log.error("Not able to get UserDetails ",ex);
        }
        return new ResponseEntity <>(user,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
