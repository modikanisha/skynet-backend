package com.skynet.employeemgmt.service;
import com.skynet.commons.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Page<Employee> list(Pageable pageable);
}
