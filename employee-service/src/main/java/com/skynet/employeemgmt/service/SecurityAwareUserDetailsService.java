package com.skynet.employeemgmt.service;

import com.skynet.commons.models.Employee;
import com.skynet.commons.repository.EmployeeRepository;
import com.skynet.employeemgmt.utils.EmployeeUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SecurityAwareUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final Employee employee = employeeRepository.findByEmailId(email);

        if(employee == null){
            throw new UsernameNotFoundException(email);
        }
        return EmployeeUtils.employeeDetails(employee);
    }

}