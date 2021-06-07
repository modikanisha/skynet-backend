package com.skynet.employeemgmt.utils;

import com.skynet.commons.models.Employee;
import com.skynet.jwtsecurity.EmployeeManagement;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;


public final class EmployeeUtils {
    private EmployeeUtils() {
    }

    public static org.springframework.security.core.userdetails.User employeeDetails(Employee employee) {
        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("IU");

        return new EmployeeManagement(employee.getEmailId(), employee.getPassCode(),
                true, true,
                true, true,
                Collections.singletonList(authority));
    }

    public static String getRoleNames(Employee employee) {
        if (employee.getRole() == null) {
            return null;
        }
        return employee.getRole().getRoleName();
    }

}
