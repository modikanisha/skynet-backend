package com.skynet.employeemgmt.controller;

import com.skynet.commons.models.Employee;
import com.skynet.commons.models.Role;
import com.skynet.commons.repository.EmployeeRepository;
import com.skynet.employeemgmt.dto.EmployeeDto;
import com.skynet.employeemgmt.model.Login;
import com.skynet.employeemgmt.service.AuthenticationService;
import com.skynet.employeemgmt.service.SecurityAwareUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.skynet.commons.enums.Status.ACTIVE;
import static com.skynet.commons.enums.Status.INACTIVE;

@RestController
@AllArgsConstructor
@RequestMapping("/authenticate")
@Slf4j
public class AuthenticationController {

    private final EmployeeRepository employeeRepository;
    private final AuthenticationService authenticationService;
    //TODO
   // private final LoginOtpService loginOtpService;
    private final SecurityAwareUserDetailsService userDetailsService;

    @GetMapping("/test")
    public String greeting() {
        return "Success";
    }

//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Logged in successfully"),
//            @ApiResponse(code = 400, message = "User not confirmed registration"),
//            @ApiResponse(code = 400, message = "Incorrect email address or password")})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.info("Login API invoked for authentication");
        String email = login.getEmail().toLowerCase();
        final Employee employee = employeeRepository.findByEmailId(email);
        Employee updatedUser = null;
        EmployeeDto employeeDto = null;
        if (employee != null) {
            if (employee.getStatus() == INACTIVE.getName()) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>("User is inactive, Please contact administrator!", status);
            }
            Role role = employee.getRole();
            AtomicBoolean isDeactive = new AtomicBoolean(true);

                if (role.getStatus().equalsIgnoreCase(ACTIVE.getName())) {
                    isDeactive.set(false);
                }

            if (isDeactive.get()) {
                log.info("User is deactive");
                HttpStatus status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity("User associated with role is inactive, Please contact administrator!",
                        status);
            }

            String ipAddress = authenticationService.getIpAddresss(request);
            authenticationService.logIn(login, request, response, ipAddress);

            //TODO
//            if (authenticationService.mfaRequired(optionalUser.get(), request))
//                updatedUser = loginOtpService.generateOtp(login.getEmail());

            employeeDto = authenticationService.createEmployeeDTO(employee);
            Employee modifiedUser = employeeDto.getEmployee();
            if (updatedUser != null && modifiedUser != null) {
               /* modifiedUser.setLoginOTP(updatedUser.getLoginOTP());
                modifiedUser.setOtpExpiryTimestamp(updatedUser.getOtpExpiryTimestamp());*/
            }
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        }
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/validateOtp")
//    public ResponseEntity validateOTP(@RequestBody OtpLoginDTO otpLoginDTO, HttpServletResponse response,
//                                      HttpServletRequest request) throws Exception {
//        loginOtpService.validateOtp(otpLoginDTO.getEmailId(), otpLoginDTO.getOtp());
//
//        // if otp is valid generate auth token for user
//        String ipAddress = authenticationService.getIpAddresss(request);
//        userDetailsService.loadUserByUsername(otpLoginDTO.getEmailId());
//        authenticationService.logInforMFA(otpLoginDTO.getEmailId(), ipAddress, response);
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        authenticationService.logOut(response);
        return ResponseEntity.ok().build();
    }

}

