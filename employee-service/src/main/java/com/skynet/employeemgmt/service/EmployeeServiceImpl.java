package com.skynet.employeemgmt.service;
import com.skynet.employeemgmt.models.Employee;
import com.skynet.employeemgmt.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

//    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
//        employee.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        employee.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        employeeRepository.save(employee);
        return employee;
    }
}
