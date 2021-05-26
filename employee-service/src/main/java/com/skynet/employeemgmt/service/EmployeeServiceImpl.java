package com.skynet.employeemgmt.service;

import com.skynet.commons.models.Employee;
import com.skynet.employeemgmt.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

//    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setDescription("Kanisha static");
        employee.setStatusId("1");
        employeeRepository.save(employee);
        return employee;
    }
}
