//package com.skynet.employeemgmt.service;
//
//import com.hhstechgroup.ryl.commons.constants.UserConstants;
//import com.hhstechgroup.ryl.commons.models.entities.User;
//import com.hhstechgroup.ryl.commons.services.notification.ExternalMessageService;
//import com.hhstechgroup.usermgmt.exception.UserBadCredentialsException;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.security.SecureRandom;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//import static com.hhstechgroup.ryl.commons.constants.UserConstants.SUBJECT;
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class LoginOtpService {
//
//    private final UsersService usersService;
//    private final ExternalMessageService messageService;
//    private final PasswordPolicyConfigService passwordPolicyConfigService;
//    private static Integer EXPIRE_MINS = 5; // default value is 5
//
//    public User generateOtp(String email) throws Exception {
//          if(passwordPolicyConfigService.getPasswordPolicyConfig().getOtpExpiry() !=
//          null) {
//              EXPIRE_MINS = Integer.valueOf(passwordPolicyConfigService.getPasswordPolicyConfig().getOtpExpiry());
//          }
//        SecureRandom r = new SecureRandom();
//        int number = r.nextInt(999999);
//        String otp = String.format("%06d", number);
//
//        // store otp in db or cache as <email , otp>
//        User user = usersService.findUserByMail(email);
//        user.setLoginOTP(otp);
//        user.setOtpExpiryTimestamp(Instant.now().plus(EXPIRE_MINS, ChronoUnit.MINUTES));
//        usersService.updateUser(user);
//        sendOtp(user);
//
//        return user;
//    }
//
//    public void sendOtp(User user) throws Exception {
//        if (passwordPolicyConfigService.getPasswordPolicyConfig().getOtpExpiry() != null) {
//            EXPIRE_MINS = Integer.valueOf(passwordPolicyConfigService.getPasswordPolicyConfig().getOtpExpiry());
//        }
//        final Map<String, Object> parameters = new HashMap<>();
//        parameters.put(SUBJECT, UserConstants.OTP_FOR_LOGIN);
//        parameters.put(UserConstants.EMAIL, user.getEmail());
//        parameters.put(UserConstants.OTP, user.getLoginOTP());
//        parameters.put(UserConstants.OTP_EXPIRY, UserConstants.EXPIRE_MINS);
//        parameters.put(UserConstants.FIRSTNAME, user.getFirstName());
//        parameters.put(UserConstants.LASTNAME, user.getLastName());
//        parameters.put(UserConstants.FULLNAME, user.getFullName());
//        parameters.put(UserConstants.FOLDERNAME, UserConstants.NOTIFICATION);
//        messageService.sendOTPEmail(
//                user.getEmail(),
//                UserConstants.OTP_MAIL,
//                UserConstants.LINK,
//                UserConstants.LOGO_LINK,
//                parameters
//        );
//    }
//
//    public void validateOtp(String email, String otp) throws ExecutionException {
//        // fetch otp from db
//
//        User user = usersService.findUserByMail(email);
//        if (user.getLoginOTP() == null || user.getOtpExpiryTimestamp() == null) // otp and expiry must be present in db
//        {
//            throw new UserBadCredentialsException(email, "Verification failed. Please try again");
//        } else {
//            if (user.getOtpExpiryTimestamp().isBefore(Instant.now())) // otp has expired
//            {
//                clearOTPfromDB(user);
//                throw new UserBadCredentialsException(email, "OTP has expired. Please generate new OTP and try again");
//            } else if (!user.getLoginOTP().equals(otp)) {
//                throw new UserBadCredentialsException(email, "Verification failed. Please try again");
//            }
//        }
//
//        clearOTPfromDB(user);
//    }
//
//    public void clearOTPfromDB(User user) {
//        user.setOtpExpiryTimestamp(null);
//        user.setLoginOTP(null);
//        usersService.updateUser(user);
//    }
//}
