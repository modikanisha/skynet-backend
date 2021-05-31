package com.skynet.employeemgmt.service;

import com.skynet.commons.models.Employee;
import com.skynet.commons.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.skynet.commons.enums.Status.ACTIVE;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setDescription("Kanisha static");
        employee.setStatus(ACTIVE.getName());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Page<Employee> list(Pageable pageable) {
     return  employeeRepository.findAll(pageable);
    }
}
