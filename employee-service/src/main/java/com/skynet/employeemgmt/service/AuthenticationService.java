package com.skynet.employeemgmt.service;


import com.skynet.commons.models.Employee;
import com.skynet.commons.repository.EmployeeRepository;
import com.skynet.employeemgmt.dto.EmployeeDto;
import com.skynet.employeemgmt.exception.*;
import com.skynet.employeemgmt.model.Login;
import com.skynet.jwtsecurity.TokenService;
import com.skynet.jwtsecurity.utils.CookieCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.sql.Timestamp;

@Service
@AllArgsConstructor
@Slf4j
@Component
public class AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final SecurityAwareUserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final CookieCreator cookieCreator;
    private final PasswordEncoder passwordEncoder;


    public void logIn(final Login login, final HttpServletRequest request, final HttpServletResponse response,
                      String ipAddress) {
        /*
         * if (!(isTPtoP != null && isTPtoP.equalsIgnoreCase("Yes"))) {
         * validationService.validateLogin(loginVM); }
         */
        try {
            final String email = login.getEmail().toLowerCase();
            final boolean rememberMe = login.isRememberMe();
            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            //if (!(isTPtoP != null && isTPtoP.equalsIgnoreCase("Yes"))) {
            validateCredentials(userDetails, login.getPassword(), true,email,ipAddress);
            //}


            // Do not generate token if we need MFA for this user
            Employee employee = employeeRepository.findByEmailId(login.getEmail().toLowerCase());
            if (employee != null)
                logIn(email, rememberMe, userDetails, response, ipAddress);

        } catch (UserNotFoundException e) {
            throw new UserLoginException(login.getEmail(), e.getMessage(), e);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new UserLoginException(login.getEmail(), e.getMessage(), e);
        }
    }

    private void validateCredentials(final UserDetails userDetails, final String password, final boolean encoded, String email, String ipAddress) {
        /*if (!userDetails.isEnabled()) {
            throw new DisabledException("Please check your email to confirm your registration");
        }*/
        if (StringUtils.isEmpty(password)) {
            throw new UserBadCredentialsException(userDetails.getUsername());
        }
        //TODO
        //TODO encoded == true for temporary purpose
        final String userDetailsPassword = userDetails.getPassword();
        boolean isPasswordMatches = encoded ? StringUtils.equals(password, userDetailsPassword) :
                passwordEncoder.matches(password, userDetailsPassword);
        if(!isPasswordMatches){
            throw new UserBadCredentialsException(userDetails.getUsername());
        }
       Employee employee = employeeRepository.findByEmailId(userDetails.getUsername());
        if (employee == null){
            throw new UserNotFoundException(userDetails.getUsername());
        }
         // passwordExpirationCheck(userByEmail, isPasswordMatches, userDetails);
    }

    public String getIpAddresss(HttpServletRequest request) throws Exception {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
                remoteAddr = request.getRemoteAddr();
                if (remoteAddr != null && remoteAddr.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    String ipAddress = inetAddress.getHostAddress();
                    remoteAddr = ipAddress;
                }
            }
        }
        return remoteAddr;
    }

    private void logIn(final String email, final boolean rememberMe, final UserDetails userDetails,
                       final HttpServletResponse response, String ipAddress) {
        final Authentication authentication = createAuthentication(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = tokenService.createToken(authentication, rememberMe);
        final Cookie jwtCookie = cookieCreator.createJwtCookie(jwt, rememberMe);
        response.addCookie(jwtCookie);

        Employee loggedInEmployee = employeeRepository.findByEmailId(email);
        loggedInEmployee.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
        employeeRepository.save(loggedInEmployee);
    }

    private Authentication createAuthentication(final UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

//    public boolean mfaRequired(User user, HttpServletRequest request) {
//        boolean mfaEnabled = false;
//        PasswordPolicyConfig passwordPolicyConfig = passwordPolicyConfigService.getPasswordPolicyConfig();
//
//        if (((user.getType() == PortalType.IU) && passwordPolicyConfig.getIsMFAforIU())
//                || ((user.getType() != PortalType.IU) && passwordPolicyConfig.getIsMFAforProvider()))
//            return true;
//
//        // check user has MFA enabled or not
//        if (user.isMfaEnabled()) {
//            return mfaEnabled;
//        }
//        return mfaEnabled;
//    }

    public EmployeeDto createEmployeeDTO(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee u = new Employee();
        u.setEmailId(employee.getEmailId());
        u.setName(employee.getName());
        employeeDto.setEmployee(u);
        return employeeDto;
    }

    public void logInforMFA(final String email, String ipAddress, final HttpServletResponse response) {
        final String token = tokenService.getToken();
        if (StringUtils.isNotEmpty(token)) {
            throw new InvalidArgumentException("Already logged in.");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        logIn(email, true, userDetails, response, ipAddress);
    }

    public void logOut(final HttpServletResponse response) {
        String userEmail = tokenService.getUserEmailFromHttpRequest().orElseThrow(UserUnauthorizedException::new);
        SecurityContextHolder.getContext().setAuthentication(null);
        response.addCookie(cookieCreator.invalidateJwtCookie());
        cookieCreator.invalidateCookies().forEach(response::addCookie);;
        tokenService.cacheEvict(tokenService.getToken());
    }

}
